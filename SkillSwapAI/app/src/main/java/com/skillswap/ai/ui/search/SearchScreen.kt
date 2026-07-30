package com.skillswap.ai.ui.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skillswap.ai.ui.components.*
import com.skillswap.ai.ui.theme.Blue40
import com.skillswap.ai.ui.theme.Blue90

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onNavigateToStudentProfile: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showFiltersSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        // Header
        GradientBanner {
            Column {
                Text(
                    "Find Students",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "${uiState.results.size} student${if (uiState.results.size != 1) "s" else ""} available",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(0.8f)
                )
                Spacer(Modifier.height(16.dp))

                // Search Field
                OutlinedTextField(
                    value = uiState.query,
                    onValueChange = viewModel::onQueryChange,
                    placeholder = { Text("Search students...", color = Color.White.copy(0.7f)) },
                    leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color.White) },
                    trailingIcon = {
                        if (uiState.query.isNotBlank()) {
                            IconButton(onClick = { viewModel.onQueryChange("") }) {
                                Icon(Icons.Filled.Clear, null, tint = Color.White)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { viewModel.performSearch() }),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White.copy(0.8f),
                        unfocusedBorderColor = Color.White.copy(0.4f),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    )
                )
            }
        }

        // Filters Button Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val activeFiltersCount = (if (uiState.selectedCollege != null) 1 else 0) +
                                     (if (uiState.selectedDepartment != null) 1 else 0) +
                                     (if (uiState.selectedSkills.isNotEmpty()) 1 else 0)
                                     
            OutlinedButton(
                onClick = { showFiltersSheet = true },
                shape = RoundedCornerShape(50),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Icon(Icons.Filled.FilterList, contentDescription = "Filters", modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Filters" + if (activeFiltersCount > 0) " ($activeFiltersCount)" else "")
            }
            
            if (activeFiltersCount > 0 || uiState.hasSearched) {
                TextButton(onClick = { viewModel.clearFilters() }) {
                    Text("Clear All", color = MaterialTheme.colorScheme.error)
                }
            }
        }

        // Filters Bottom Sheet
        if (showFiltersSheet) {
            ModalBottomSheet(
                onDismissRequest = { showFiltersSheet = false },
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                FiltersBottomSheetContent(
                    uiState = uiState,
                    viewModel = viewModel,
                    onClose = { showFiltersSheet = false }
                )
            }
        }

        // Results
        if (uiState.isLoading) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) { repeat(4) { ShimmerCard() } }
        } else if (uiState.allUsers.isEmpty()) {
            EmptyState(
                emoji = "👥",
                title = "No students available",
                subtitle = "Check back later when more students join!"
            )
        } else if (uiState.results.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmptyState(
                    emoji = "🔍",
                    title = "No students match your selected filters.",
                    subtitle = "Try adjusting your search criteria."
                )
                Spacer(Modifier.height(16.dp))
                Button(onClick = { viewModel.clearFilters() }) {
                    Text("Clear Filters")
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(uiState.results, key = { it.uid }) { user ->
                    StudentCard(
                        user = user,
                        onClick = { onNavigateToStudentProfile(user.uid) }
                    )
                }
                item { Spacer(Modifier.height(80.dp)) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersBottomSheetContent(
    uiState: SearchUiState,
    viewModel: SearchViewModel,
    onClose: () -> Unit
) {
    var collegeDropdownExpanded by remember { mutableStateOf(false) }
    var departmentDropdownExpanded by remember { mutableStateOf(false) }
    var skillsDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
            .padding(bottom = 32.dp)
    ) {
        Text(
            "Filters", 
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // College
        Text("College", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { collegeDropdownExpanded = !collegeDropdownExpanded }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(uiState.selectedCollege ?: "Select College", color = if (uiState.selectedCollege != null) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(0.6f))
                    Icon(if (collegeDropdownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, null)
                }
            }
            AnimatedVisibility(visible = collegeDropdownExpanded) {
                Surface(
                    shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                        uiState.availableColleges.forEach { college ->
                            Text(
                                text = college,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.onCollegeChange(college)
                                        collegeDropdownExpanded = false
                                    }
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Department
        Text("Department", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, if (uiState.selectedCollege != null) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.outline.copy(0.3f)),
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = uiState.selectedCollege != null) { departmentDropdownExpanded = !departmentDropdownExpanded }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(uiState.selectedDepartment ?: "Select Department", color = if (uiState.selectedDepartment != null) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(if (uiState.selectedCollege != null) 0.6f else 0.3f))
                    Icon(if (departmentDropdownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, null, tint = if (uiState.selectedCollege != null) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(0.3f))
                }
            }
            AnimatedVisibility(visible = departmentDropdownExpanded) {
                Surface(
                    shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                        uiState.availableDepartments.forEach { dept ->
                            Text(
                                text = dept,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.onDepartmentChange(dept)
                                        departmentDropdownExpanded = false
                                    }
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Skills
        Text("Skills", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { skillsDropdownExpanded = !skillsDropdownExpanded }
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val skillText = if (uiState.selectedSkills.isEmpty()) "Select Skills" else "${uiState.selectedSkills.size} skills selected"
                    Text(skillText, color = if (uiState.selectedSkills.isNotEmpty()) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(0.6f))
                    Icon(if (skillsDropdownExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, null)
                }
            }
            AnimatedVisibility(visible = skillsDropdownExpanded) {
                Surface(
                    shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                        if (uiState.availableSkills.isEmpty()) {
                            Text("No skills available", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.bodyMedium)
                        } else {
                            uiState.availableSkills.forEach { skill ->
                                val isSelected = uiState.selectedSkills.contains(skill)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { viewModel.onSkillToggled(skill) }
                                        .padding(horizontal = 16.dp, vertical = 12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(skill, color = if (isSelected) Blue40 else MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.bodyMedium)
                                    if (isSelected) {
                                        Icon(Icons.Filled.Check, null, tint = Blue40, modifier = Modifier.size(20.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Sort By
        Text("Sort By", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { viewModel.onSortOrderChange(SortOrder.HIGHEST_RATED_FIRST) }) {
                RadioButton(
                    selected = uiState.sortOrder == SortOrder.HIGHEST_RATED_FIRST,
                    onClick = { viewModel.onSortOrderChange(SortOrder.HIGHEST_RATED_FIRST) }
                )
                Text("Highest Rated", style = MaterialTheme.typography.bodyMedium)
            }
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { viewModel.onSortOrderChange(SortOrder.LOWEST_RATED_FIRST) }) {
                RadioButton(
                    selected = uiState.sortOrder == SortOrder.LOWEST_RATED_FIRST,
                    onClick = { viewModel.onSortOrderChange(SortOrder.LOWEST_RATED_FIRST) }
                )
                Text("Lowest Rated", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Spacer(Modifier.height(32.dp))

        // Actions
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedButton(
                onClick = { viewModel.clearFilters() },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Clear All")
            }
            Button(
                onClick = onClose,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Blue40)
            ) {
                Text("Apply Filters")
            }
        }
    }
}
