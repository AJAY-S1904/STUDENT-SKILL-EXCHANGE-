package com.skillswap.ai.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillswap.ai.data.model.User
import com.skillswap.ai.data.repository.AuthRepository
import com.skillswap.ai.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class SortOrder { HIGHEST_RATED_FIRST, LOWEST_RATED_FIRST }

data class SearchUiState(
    val query: String = "",
    val selectedCollege: String? = null,
    val selectedDepartment: String? = null,
    val selectedSkills: Set<String> = emptySet(),
    val sortOrder: SortOrder = SortOrder.HIGHEST_RATED_FIRST,
    
    val availableColleges: List<String> = emptyList(),
    val availableDepartments: List<String> = emptyList(),
    val availableSkills: List<String> = emptyList(),
    
    val results: List<User> = emptyList(),
    val allUsers: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val hasSearched: Boolean = false
)

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    val currentUserId: String get() = authRepository.currentUserId

    init {
        loadAllUsers()
    }

    private fun loadAllUsers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            authRepository.currentUserIdFlow.flatMapLatest { uid ->
                if (uid.isEmpty()) {
                    kotlinx.coroutines.flow.flowOf(emptyList())
                } else {
                    userRepository.getAllUsersFlow().map { users ->
                        users.filter { it.uid != uid }
                    }
                }
            }.collect { filtered ->
                _uiState.update { state -> 
                    val invalidColleges = listOf("CSE", "IT", "ECE", "EEE", "AI & DS", "Mechanical", "Civil", "MECH", "CIVIL")
                    val invalidDepartments = listOf("CIT", "PSG", "KCT", "GCT")

                    val colleges = filtered.map { it.college }
                        .filter { it.isNotBlank() && !invalidColleges.any { invalid -> it.equals(invalid, ignoreCase = true) } }
                        .distinct().sorted()
                    
                    val skills = filtered.flatMap { it.teachSkills + it.learnSkills }.filter { it.isNotBlank() }.distinct().sorted()
                    
                    val depts = if (state.selectedCollege != null) {
                        filtered.filter { it.college == state.selectedCollege }
                                .map { it.department }
                                .filter { it.isNotBlank() && !invalidDepartments.any { invalid -> it.equals(invalid, ignoreCase = true) } }
                                .distinct().sorted()
                    } else emptyList()

                    val results = applyFilters(filtered, state.query, state.selectedCollege, state.selectedDepartment, state.selectedSkills, state.sortOrder)
                    
                    state.copy(
                        allUsers = filtered,
                        availableColleges = colleges,
                        availableSkills = skills,
                        availableDepartments = depts,
                        results = results,
                        isLoading = false,
                        hasSearched = state.query.isNotBlank() || state.selectedCollege != null || state.selectedSkills.isNotEmpty()
                    )
                }
            }
        }
    }

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
        performSearch()
    }

    fun onCollegeChange(college: String) {
        _uiState.update { state -> 
            val invalidDepartments = listOf("CIT", "PSG", "KCT", "GCT")
            val depts = state.allUsers.filter { it.college == college }
                .map { it.department }
                .filter { it.isNotBlank() && !invalidDepartments.any { invalid -> it.equals(invalid, ignoreCase = true) } }
                .distinct().sorted()
            state.copy(selectedCollege = college, selectedDepartment = null, availableDepartments = depts)
        }
        performSearch()
    }

    fun onDepartmentChange(department: String) {
        _uiState.update { it.copy(selectedDepartment = department) }
        performSearch()
    }

    fun onSkillToggled(skill: String) {
        _uiState.update { state ->
            val newSkills = if (state.selectedSkills.contains(skill)) {
                state.selectedSkills - skill
            } else {
                state.selectedSkills + skill
            }
            state.copy(selectedSkills = newSkills)
        }
        performSearch()
    }

    fun onSortOrderChange(order: SortOrder) {
        _uiState.update { it.copy(sortOrder = order) }
        performSearch()
    }

    fun performSearch() {
        _uiState.update { state ->
            val results = applyFilters(state.allUsers, state.query, state.selectedCollege, state.selectedDepartment, state.selectedSkills, state.sortOrder)
            state.copy(
                results = results, 
                hasSearched = state.query.isNotBlank() || state.selectedCollege != null || state.selectedSkills.isNotEmpty()
            )
        }
    }

    fun clearFilters() {
        _uiState.update { 
            it.copy(
                query = "", 
                selectedCollege = null, 
                selectedDepartment = null, 
                selectedSkills = emptySet(),
                availableDepartments = emptyList(),
                sortOrder = SortOrder.HIGHEST_RATED_FIRST
            ) 
        }
        performSearch()
    }

    private fun applyFilters(
        allUsers: List<User>, 
        query: String, 
        college: String?, 
        department: String?, 
        skills: Set<String>, 
        sortOrder: SortOrder
    ): List<User> {
        var filtered = allUsers
        
        if (college != null) {
            filtered = filtered.filter { it.college == college }
        }
        if (department != null) {
            filtered = filtered.filter { it.department == department }
        }
        if (skills.isNotEmpty()) {
            filtered = filtered.filter { user -> 
                val userSkills = user.teachSkills + user.learnSkills
                skills.any { s -> userSkills.contains(s) }
            }
        }
        if (query.isNotBlank()) {
            filtered = filtered.filter { user ->
                user.name.contains(query, true) ||
                user.teachSkills.any { it.contains(query, true) } ||
                user.learnSkills.any { it.contains(query, true) } ||
                user.college.contains(query, true) ||
                user.department.contains(query, true)
            }
        }
        
        return when (sortOrder) {
            SortOrder.HIGHEST_RATED_FIRST -> filtered.sortedByDescending { it.rating }
            SortOrder.LOWEST_RATED_FIRST -> filtered.sortedBy { it.rating }
        }
    }
}
