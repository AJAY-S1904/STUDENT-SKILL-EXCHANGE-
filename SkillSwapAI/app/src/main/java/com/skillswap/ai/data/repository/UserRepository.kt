package com.skillswap.ai.data.repository

import android.net.Uri
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.skillswap.ai.data.model.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database: FirebaseDatabase,
    private val storage: FirebaseStorage
) {
    private val usersRef = database.getReference("Users")

    suspend fun createUser(user: User): AuthResult<Unit> {
        return try {
            usersRef.child(user.uid).setValue(user).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to create user")
        }
    }

    suspend fun getUser(uid: String): AuthResult<User> {
        return try {
            val snapshot = usersRef.child(uid).get().await()
            val user = snapshot.getValue(User::class.java)
            if (user != null) AuthResult.Success(user.copy(uid = uid))
            else AuthResult.Error("User not found")
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to get user")
        }
    }

    fun getUserFlow(uid: String): Flow<User?> = callbackFlow {
        val ref = usersRef.child(uid)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                trySend(user?.copy(uid = snapshot.key ?: uid))
            }
            override fun onCancelled(error: DatabaseError) { trySend(null) }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    suspend fun updateUser(uid: String, updates: Map<String, Any>): AuthResult<Unit> {
        return try {
            usersRef.child(uid).updateChildren(updates).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to update user")
        }
    }

    suspend fun uploadProfilePicture(uid: String, uri: Uri): AuthResult<String> {
        return kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()

                if (bitmap == null) {
                    throw Exception("Failed to decode image from gallery")
                }

                // Resize bitmap to a smaller size to fit comfortably in the realtime database
                val maxSide = 200f
                val scale = Math.min(maxSide / bitmap.width, maxSide / bitmap.height)
                val resized = if (scale < 1) {
                    Bitmap.createScaledBitmap(bitmap, (bitmap.width * scale).toInt(), (bitmap.height * scale).toInt(), true)
                } else bitmap

                // Compress to JPEG and encode to Base64 string
                val outputStream = ByteArrayOutputStream()
                resized.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
                val byteArray = outputStream.toByteArray()
                val base64 = Base64.encodeToString(byteArray, Base64.NO_WRAP)
                
                // Format as a data URI so Coil can render it perfectly
                val url = "data:image/jpeg;base64,$base64"
                
                usersRef.child(uid).child("profilePictureUrl").setValue(url)
                    .addOnSuccessListener {
                        android.os.Handler(android.os.Looper.getMainLooper()).post {
                            android.widget.Toast.makeText(context, "Photo uploaded successfully!", android.widget.Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener { e ->
                        android.util.Log.e("UserRepository", "Firebase save failed", e)
                        android.os.Handler(android.os.Looper.getMainLooper()).post {
                            android.widget.Toast.makeText(context, "Sync Error: ${e.message}", android.widget.Toast.LENGTH_LONG).show()
                        }
                    }
                
                AuthResult.Success(url)
            } catch (e: Exception) {
                android.util.Log.e("UserRepository", "Upload failed", e)
                android.os.Handler(android.os.Looper.getMainLooper()).post {
                    android.widget.Toast.makeText(context, "Error: ${e.message}", android.widget.Toast.LENGTH_LONG).show()
                }
                AuthResult.Error(e.message ?: "Failed to upload picture")
            }
        }
    }

    suspend fun getAllUsers(): AuthResult<List<User>> {
        return try {
            val snapshot = usersRef.get().await()
            val users = mutableListOf<User>()
            snapshot.children.forEach { child ->
                child.getValue(User::class.java)?.let {
                    if (it.isActive) {
                        users.add(it.copy(uid = child.key ?: ""))
                    }
                }
            }
            AuthResult.Success(users)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to get users")
        }
    }

    fun getAllUsersFlow(): Flow<List<User>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = mutableListOf<User>()
                snapshot.children.forEach { child ->
                    child.getValue(User::class.java)?.let {
                        if (it.isActive) {
                            users.add(it.copy(uid = child.key ?: ""))
                        }
                    }
                }
                trySend(users)
            }
            override fun onCancelled(error: DatabaseError) {
                trySend(emptyList())
            }
        }
        usersRef.addValueEventListener(listener)
        awaitClose { usersRef.removeEventListener(listener) }
    }

    suspend fun searchUsersBySkill(skill: String): AuthResult<List<User>> {
        return try {
            val snapshot = usersRef.get().await()
            val users = mutableListOf<User>()
            snapshot.children.forEach { child ->
                val user = child.getValue(User::class.java)?.copy(uid = child.key ?: "")
                if (user != null && user.teachSkills.any { it.contains(skill, ignoreCase = true) }) {
                    users.add(user)
                }
            }
            AuthResult.Success(users)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Search failed")
        }
    }

    suspend fun searchUsersByCollege(college: String): AuthResult<List<User>> {
        return try {
            val snapshot = usersRef.orderByChild("college").equalTo(college).get().await()
            val users = mutableListOf<User>()
            snapshot.children.forEach { child ->
                child.getValue(User::class.java)?.let {
                    users.add(it.copy(uid = child.key ?: ""))
                }
            }
            AuthResult.Success(users)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Search failed")
        }
    }

    suspend fun searchUsersByDepartment(department: String): AuthResult<List<User>> {
        return try {
            val snapshot = usersRef.orderByChild("department").equalTo(department).get().await()
            val users = mutableListOf<User>()
            snapshot.children.forEach { child ->
                child.getValue(User::class.java)?.let {
                    users.add(it.copy(uid = child.key ?: ""))
                }
            }
            AuthResult.Success(users)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Search failed")
        }
    }

    suspend fun updateFcmToken(uid: String, token: String): AuthResult<Unit> {
        return try {
            usersRef.child(uid).child("fcmToken").setValue(token).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to update FCM token")
        }
    }

    suspend fun updateTeachSkills(uid: String, skills: List<String>): AuthResult<Unit> {
        return try {
            usersRef.child(uid).child("teachSkills").setValue(skills).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to update skills")
        }
    }

    suspend fun updateLearnSkills(uid: String, skills: List<String>): AuthResult<Unit> {
        return try {
            usersRef.child(uid).child("learnSkills").setValue(skills).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to update skills")
        }
    }
}
