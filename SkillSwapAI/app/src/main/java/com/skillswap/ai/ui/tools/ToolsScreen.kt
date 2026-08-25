package com.skillswap.ai.ui.tools

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.WorkHistory
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skillswap.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToCareerAnalysis: () -> Unit,
    onNavigateToLearningRoadmap: () -> Unit,
    onNavigateToSkillPortfolio: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tools", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Improve, plan and showcase your skills",
                style = MaterialTheme.typography.bodyLarge,
                color = Neutral20,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            val tools = listOf(
                ToolItem(
                    title = "Career Analysis",
                    description = "Analyze your skill gaps and readiness for your career goals.",
                    icon = Icons.Default.BarChart,
                    color = Blue40,
                    onClick = onNavigateToCareerAnalysis
                ),
                ToolItem(
                    title = "Learning Roadmap",
                    description = "View your personalized path to master target skills.",
                    icon = Icons.Default.Assignment,
                    color = Purple40,
                    onClick = onNavigateToLearningRoadmap
                ),
                ToolItem(
                    title = "Skill Portfolio",
                    description = "Showcase your verified skills and completed roadmaps.",
                    icon = Icons.Default.WorkHistory,
                    color = Color(0xFFE65100), // Orange
                    onClick = onNavigateToSkillPortfolio
                )
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(tools) { tool ->
                    ToolCard(tool)
                }
            }
        }
    }
}

data class ToolItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color,
    val onClick: () -> Unit
)

@Composable
fun ToolCard(tool: ToolItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = tool.onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = tool.color.copy(alpha = 0.1f),
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = tool.icon,
                    contentDescription = tool.title,
                    tint = tool.color,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tool.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = tool.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Neutral20
                )
            }
        }
    }
}
