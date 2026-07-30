package com.skillswap.ai.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.skillswap.ai.data.model.Rating
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatingRepository @Inject constructor(
    private val database: FirebaseDatabase
) {
    private val ratingsRef = database.getReference("Ratings")
    private val usersRef = database.getReference("Users")

    suspend fun submitRating(rating: Rating): AuthResult<Unit> {
        return try {
            val newRef = ratingsRef.push()
            newRef.setValue(rating.copy(id = newRef.key ?: "")).await()
            updateUserRating(rating.ratedUserId)
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to submit rating")
        }
    }

    private suspend fun updateUserRating(userId: String) {
        try {
            val snapshot = ratingsRef.orderByChild("ratedUserId").equalTo(userId).get().await()
            val ratings = mutableListOf<Rating>()
            snapshot.children.forEach { child ->
                child.getValue(Rating::class.java)?.let { ratings.add(it) }
            }
            if (ratings.isNotEmpty()) {
                val avg = ratings.map { it.stars }.average()
                usersRef.child(userId).updateChildren(
                    mapOf("rating" to avg, "ratingCount" to ratings.size)
                ).await()
            }
        } catch (_: Exception) {}
    }

    suspend fun getRatingsForUser(userId: String): AuthResult<List<Rating>> {
        return try {
            val snapshot = ratingsRef.orderByChild("ratedUserId").equalTo(userId).get().await()
            val ratings = mutableListOf<Rating>()
            snapshot.children.forEach { child ->
                child.getValue(Rating::class.java)?.let {
                    ratings.add(it.copy(id = child.key ?: ""))
                }
            }
            AuthResult.Success(ratings)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to get ratings")
        }
    }

    suspend fun hasRatedSession(raterId: String, sessionId: String): Boolean {
        return try {
            val snapshot = ratingsRef.orderByChild("sessionId").equalTo(sessionId).get().await()
            snapshot.children.any { child ->
                child.child("raterId").getValue(String::class.java) == raterId
            }
        } catch (_: Exception) { false }
    }
}
