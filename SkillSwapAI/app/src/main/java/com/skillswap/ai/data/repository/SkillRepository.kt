package com.skillswap.ai.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.skillswap.ai.data.model.Skill
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SkillRepository @Inject constructor(
    private val database: FirebaseDatabase
) {
    private val skillsRef = database.getReference("Skills")

    suspend fun getAllSkills(): AuthResult<List<Skill>> {
        return try {
            val snapshot = skillsRef.get().await()
            val skills = mutableListOf<Skill>()
            snapshot.children.forEach { child ->
                child.getValue(Skill::class.java)?.let { skills.add(it) }
            }
            AuthResult.Success(skills)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to load skills")
        }
    }

    suspend fun addSkill(skill: Skill): AuthResult<Unit> {
        return try {
            val newRef = skillsRef.push()
            newRef.setValue(skill).await()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to add skill")
        }
    }

    suspend fun incrementPopularity(skillName: String): AuthResult<Unit> {
        return try {
            val snapshot = skillsRef.orderByChild("name").equalTo(skillName).get().await()
            snapshot.children.firstOrNull()?.let { child ->
                val current = child.child("popularityCount").getValue(Int::class.java) ?: 0
                child.ref.child("popularityCount").setValue(current + 1).await()
            }
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Failed to update skill")
        }
    }
}
