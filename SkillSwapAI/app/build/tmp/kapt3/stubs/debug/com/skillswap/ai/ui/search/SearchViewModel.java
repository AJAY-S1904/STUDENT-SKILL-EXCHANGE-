package com.skillswap.ai.ui.search;

import androidx.lifecycle.ViewModel;
import com.skillswap.ai.data.model.User;
import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006JN\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0016\u001a\u00020\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020\u001eH\u0002J\u000e\u0010 \u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u000bJ\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u000bJ\u000e\u0010\"\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u000bJ\u000e\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u000bJ\u000e\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u001cJ\u0006\u0010\'\u001a\u00020\u001eR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/skillswap/ai/ui/search/SearchViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/skillswap/ai/data/repository/AuthRepository;", "userRepository", "Lcom/skillswap/ai/data/repository/UserRepository;", "(Lcom/skillswap/ai/data/repository/AuthRepository;Lcom/skillswap/ai/data/repository/UserRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/skillswap/ai/ui/search/SearchUiState;", "currentUserId", "", "getCurrentUserId", "()Ljava/lang/String;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "applyFilters", "", "Lcom/skillswap/ai/data/model/User;", "allUsers", "query", "college", "department", "skills", "", "sortOrder", "Lcom/skillswap/ai/ui/search/SortOrder;", "clearFilters", "", "loadAllUsers", "onCollegeChange", "onDepartmentChange", "onQueryChange", "onSkillToggled", "skill", "onSortOrderChange", "order", "performSearch", "app_debug"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SearchViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.skillswap.ai.ui.search.SearchUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.search.SearchUiState> uiState = null;
    
    @javax.inject.Inject()
    public SearchViewModel(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.search.SearchUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentUserId() {
        return null;
    }
    
    private final void loadAllUsers() {
    }
    
    public final void onQueryChange(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void onCollegeChange(@org.jetbrains.annotations.NotNull()
    java.lang.String college) {
    }
    
    public final void onDepartmentChange(@org.jetbrains.annotations.NotNull()
    java.lang.String department) {
    }
    
    public final void onSkillToggled(@org.jetbrains.annotations.NotNull()
    java.lang.String skill) {
    }
    
    public final void onSortOrderChange(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.ui.search.SortOrder order) {
    }
    
    public final void performSearch() {
    }
    
    public final void clearFilters() {
    }
    
    private final java.util.List<com.skillswap.ai.data.model.User> applyFilters(java.util.List<com.skillswap.ai.data.model.User> allUsers, java.lang.String query, java.lang.String college, java.lang.String department, java.util.Set<java.lang.String> skills, com.skillswap.ai.ui.search.SortOrder sortOrder) {
        return null;
    }
}