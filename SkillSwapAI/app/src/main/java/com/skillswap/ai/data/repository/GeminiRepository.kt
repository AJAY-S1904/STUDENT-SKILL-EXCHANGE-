package com.skillswap.ai.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import com.skillswap.ai.data.model.LearningRoadmap
import com.skillswap.ai.data.model.McqTest
import com.skillswap.ai.data.model.SkillGapAnalysis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiRepository @Inject constructor(
    private val generativeModel: GenerativeModel
) {
    private val gson = Gson()

    suspend fun generateSkillGapAnalysis(
        currentSkills: List<String>,
        desiredSkill: String,
        careerGoal: String
    ): SkillGapAnalysis? = withContext(Dispatchers.IO) {
        try {
            val prompt = """
                Analyze the skill gap for a student with the following profile:
                Current Skills: ${currentSkills.joinToString(", ")}
                Desired Skill: $desiredSkill
                Career Goal: $careerGoal
                
                Generate a personalized Skill Gap Analysis in the following JSON format only (no markdown formatting, no backticks, just raw JSON).
                Follow these rules strictly:
                1. gapPercentage: Estimate the overall readiness percentage (0-100) for the selected career goal.
                2. strengths: List ONLY the student's existing strengths based on the provided current skills. Do not invent strengths unrelated to the input.
                3. weaknesses: List ONLY missing concepts, frameworks, tools, or practical experience required for the career goal. Weaknesses MUST be learning gaps (e.g. "Limited Object-Oriented Programming knowledge"), NOT programming language characteristics (e.g. "Static Typing" or "Verbose Syntax").
                4. missingSkills: Recommend the important skills required for the chosen career. Prioritize them from beginner to advanced. Do not duplicate the exact same information already shown under weaknesses.
                5. recommendations: A single string containing 3-5 concise bullet points (use \n for new lines) explaining what the student should learn first and why. Do NOT return an array for this field. Do NOT include weekly plans, timelines, priorities, or learning roadmap details.
                
                JSON Format:
                {
                  "gapPercentage": 55,
                  "strengths": ["Strong Java fundamentals", "Good programming logic"],
                  "weaknesses": ["Limited Object-Oriented Programming knowledge", "No Spring Boot knowledge"],
                  "missingSkills": ["Object-Oriented Programming", "Spring Boot", "REST APIs"],
                  "recommendations": "• Learn Object-Oriented Programming first\n• Move on to Spring Boot\n• Finally study REST API development"
                }
            """.trimIndent()

            val response = generativeModel.generateContent(prompt)
            val rawText = response.text ?: ""
            android.util.Log.e("GeminiRepository", "Raw Skill Gap Response: $rawText")
            
            var jsonText = rawText
            val startIndex = jsonText.indexOf('{')
            val endIndex = jsonText.lastIndexOf('}')
            if (startIndex != -1 && endIndex != -1 && startIndex <= endIndex) {
                jsonText = jsonText.substring(startIndex, endIndex + 1)
            }
            jsonText = jsonText.trim()
            
            android.util.Log.e("GeminiRepository", "Parsed JSON: $jsonText")
            
            val result = gson.fromJson(jsonText, SkillGapAnalysisDto::class.java)
            
            SkillGapAnalysis(
                currentSkills = currentSkills,
                desiredSkill = desiredSkill,
                careerGoal = careerGoal,
                gapPercentage = result.gapPercentage,
                strengths = result.strengths,
                weaknesses = result.weaknesses,
                missingSkills = result.missingSkills,
                recommendations = result.recommendations
            )
        } catch (e: Exception) {
            android.util.Log.e("GeminiRepository", "Error generating Skill Gap", e)
            null
        }
    }

    suspend fun generateLearningRoadmap(
        careerGoal: String,
        targetSkill: String,
        currentSkills: List<String>,
        missingSkills: List<String>
    ): LearningRoadmap? = withContext(Dispatchers.IO) {
         try {
            val prompt = """
                Create a detailed learning roadmap for a student aiming to become a $careerGoal, focusing on mastering: $targetSkill.
                Their current skills are: ${currentSkills.joinToString(", ")}.
                The identified missing skills are: ${missingSkills.joinToString(", ")}.
                
                Generate a learning roadmap in the following JSON format only (no markdown formatting, no backticks, just raw JSON).
                Follow these rules strictly:
                1. overallEstimatedWeeks: Estimate total duration in weeks.
                2. difficultyLevel: Assign Beginner, Intermediate, or Advanced based on their current skills.
                3. stages: Generate exactly 3 stages: "Beginner", "Intermediate", and "Advanced".
                4. For each stage, provide: title, estimatedDuration (e.g. "2 Weeks"), description (short), and lists of topics grouped by priority: highPriorityTopics, mediumPriorityTopics, advancedTopics.
                5. Keep all topic descriptions as concise bullet points.
                
                JSON Format:
                {
                  "overallEstimatedWeeks": 8,
                  "difficultyLevel": "Beginner",
                  "stages": [
                    {
                      "title": "Beginner Stage",
                      "estimatedDuration": "3 Weeks",
                      "description": "Learn the basics and fundamentals.",
                      "highPriorityTopics": ["Topic 1", "Topic 2"],
                      "mediumPriorityTopics": ["Topic 3"],
                      "advancedTopics": []
                    }
                  ]
                }
            """.trimIndent()

            val response = generativeModel.generateContent(prompt)
            val rawText = response.text ?: ""
            android.util.Log.e("GeminiRepository", "Raw Roadmap Response: $rawText")
            
            var jsonText = rawText
            val startIndex = jsonText.indexOf('{')
            val endIndex = jsonText.lastIndexOf('}')
            if (startIndex != -1 && endIndex != -1 && startIndex <= endIndex) {
                jsonText = jsonText.substring(startIndex, endIndex + 1)
            }
            jsonText = jsonText.trim()
            
            android.util.Log.e("GeminiRepository", "Parsed JSON: $jsonText")

            val result = gson.fromJson(jsonText, LearningRoadmapDto::class.java)
            
            LearningRoadmap(
                careerGoal = careerGoal,
                targetSkill = targetSkill,
                overallEstimatedWeeks = result.overallEstimatedWeeks,
                difficultyLevel = result.difficultyLevel,
                stages = result.stages.map { stageDto ->
                    com.skillswap.ai.data.model.LearningStage(
                        title = stageDto.title,
                        estimatedDuration = stageDto.estimatedDuration,
                        description = stageDto.description,
                        highPriorityTopics = stageDto.highPriorityTopics,
                        mediumPriorityTopics = stageDto.mediumPriorityTopics,
                        advancedTopics = stageDto.advancedTopics
                    )
                }
            )
        } catch (e: Exception) {
            android.util.Log.e("GeminiRepository", "Error generating Roadmap", e)
            null
        }
    }

    suspend fun generateMcqTest(skill: String): McqTest? = withContext(Dispatchers.IO) {
        try {
            val prompt = """
                Generate a multiple choice test to assess proficiency in the following skill: $skill.
                The test should have between 10 to 20 questions ranging from beginner to advanced difficulty.
                
                Provide the analysis in the following JSON format only (no markdown formatting, no backticks, just raw JSON):
                {
                  "questions": [
                    {
                      "question": "Sample Question?",
                      "options": ["A", "B", "C", "D"],
                      "correctAnswerIndex": 0
                    }
                  ]
                }
            """.trimIndent()

            val response = generativeModel.generateContent(prompt)
            val rawText = response.text ?: ""
            android.util.Log.e("GeminiRepository", "Raw MCQ Response: $rawText")
            
            var jsonText = rawText
            val startIndex = jsonText.indexOf('{')
            val endIndex = jsonText.lastIndexOf('}')
            if (startIndex != -1 && endIndex != -1 && startIndex <= endIndex) {
                jsonText = jsonText.substring(startIndex, endIndex + 1)
            }
            jsonText = jsonText.trim()
            
            android.util.Log.e("GeminiRepository", "Parsed JSON: $jsonText")

            val result = gson.fromJson(jsonText, McqTestDto::class.java)
            
            McqTest(
                skill = skill,
                questions = result.questions.map {
                    com.skillswap.ai.data.model.McqQuestion(
                        question = it.question,
                        options = it.options,
                        correctAnswerIndex = it.correctAnswerIndex
                    )
                }
            )
        } catch (e: Exception) {
            android.util.Log.e("GeminiRepository", "Error generating MCQ", e)
            null
        }
    }
}

// DTOs for Gson parsing
private data class SkillGapAnalysisDto(
    val gapPercentage: Int,
    val strengths: List<String>,
    val weaknesses: List<String>,
    val missingSkills: List<String>,
    val recommendations: String
)

private data class LearningRoadmapDto(
    val overallEstimatedWeeks: Int,
    val difficultyLevel: String,
    val stages: List<LearningStageDto>
)

private data class LearningStageDto(
    val title: String,
    val estimatedDuration: String,
    val description: String,
    val highPriorityTopics: List<String>,
    val mediumPriorityTopics: List<String>,
    val advancedTopics: List<String>
)

private data class McqTestDto(
    val questions: List<McqQuestionDto>
)

private data class McqQuestionDto(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
