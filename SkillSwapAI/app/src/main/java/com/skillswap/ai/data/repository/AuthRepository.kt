package com.skillswap.ai.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

sealed class AuthResult<out T> {
    data class Success<T>(val data: T) : AuthResult<T>()
    data class Error(val message: String) : AuthResult<Nothing>()
    object Loading : AuthResult<Nothing>()
}

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth
) {
    val currentUser: FirebaseUser? get() = auth.currentUser
    val currentUserId: String get() = auth.currentUser?.uid ?: ""
    val isLoggedIn: Boolean get() = auth.currentUser != null

    val currentUserIdFlow: kotlinx.coroutines.flow.Flow<String> = callbackFlow {
        val listener = com.google.firebase.auth.FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser?.uid ?: "")
        }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    suspend fun signUp(
        email: String,
        password: String,
        name: String
    ): AuthResult<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()
            result.user?.updateProfile(profileUpdates)?.await()
            AuthResult.Success(result.user!!)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Sign up failed")
        }
    }

    suspend fun login(email: String, password: String): AuthResult<FirebaseUser> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            AuthResult.Success(result.user!!)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Login failed")
        }
    }

    suspend fun loginWithGoogle(idToken: String): AuthResult<FirebaseUser> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            AuthResult.Success(result.user!!)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Google login failed")
        }
    }

    suspend fun forgotPassword(email: String): AuthResult<Unit> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to send reset email")
        }
    }

    fun logout() {
        auth.signOut()
    }
}
