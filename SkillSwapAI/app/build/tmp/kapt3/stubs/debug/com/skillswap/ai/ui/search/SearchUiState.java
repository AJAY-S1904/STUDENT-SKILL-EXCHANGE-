package com.skillswap.ai.ui.search;

import androidx.lifecycle.ViewModel;
import com.skillswap.ai.data.model.User;
import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\"\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u00a5\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0002\u0010\u0014J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000bH\u00c6\u0003J\t\u0010\'\u001a\u00020\u0012H\u00c6\u0003J\t\u0010(\u001a\u00020\u0012H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u00c6\u0003J\t\u0010,\u001a\u00020\tH\u00c6\u0003J\u000f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u00c6\u0003J\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u00c6\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u00c6\u0003J\u000f\u00100\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000bH\u00c6\u0003J\u00a9\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0012H\u00c6\u0001J\u0013\u00102\u001a\u00020\u00122\b\u00103\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00104\u001a\u000205H\u00d6\u0001J\t\u00106\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$\u00a8\u00067"}, d2 = {"Lcom/skillswap/ai/ui/search/SearchUiState;", "", "query", "", "selectedCollege", "selectedDepartment", "selectedSkills", "", "sortOrder", "Lcom/skillswap/ai/ui/search/SortOrder;", "availableColleges", "", "availableDepartments", "availableSkills", "results", "Lcom/skillswap/ai/data/model/User;", "allUsers", "isLoading", "", "hasSearched", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Set;Lcom/skillswap/ai/ui/search/SortOrder;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZZ)V", "getAllUsers", "()Ljava/util/List;", "getAvailableColleges", "getAvailableDepartments", "getAvailableSkills", "getHasSearched", "()Z", "getQuery", "()Ljava/lang/String;", "getResults", "getSelectedCollege", "getSelectedDepartment", "getSelectedSkills", "()Ljava/util/Set;", "getSortOrder", "()Lcom/skillswap/ai/ui/search/SortOrder;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class SearchUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String query = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String selectedCollege = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String selectedDepartment = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> selectedSkills = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.ui.search.SortOrder sortOrder = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> availableColleges = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> availableDepartments = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> availableSkills = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.skillswap.ai.data.model.User> results = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.skillswap.ai.data.model.User> allUsers = null;
    private final boolean isLoading = false;
    private final boolean hasSearched = false;
    
    public SearchUiState(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.Nullable()
    java.lang.String selectedCollege, @org.jetbrains.annotations.Nullable()
    java.lang.String selectedDepartment, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> selectedSkills, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.ui.search.SortOrder sortOrder, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availableColleges, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availableDepartments, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availableSkills, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.User> results, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.User> allUsers, boolean isLoading, boolean hasSearched) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSelectedCollege() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSelectedDepartment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> getSelectedSkills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.skillswap.ai.ui.search.SortOrder getSortOrder() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getAvailableColleges() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getAvailableDepartments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getAvailableSkills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.User> getResults() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.User> getAllUsers() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public final boolean getHasSearched() {
        return false;
    }
    
    public SearchUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.User> component10() {
        return null;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final boolean component12() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.skillswap.ai.ui.search.SortOrder component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.skillswap.ai.data.model.User> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.skillswap.ai.ui.search.SearchUiState copy(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.Nullable()
    java.lang.String selectedCollege, @org.jetbrains.annotations.Nullable()
    java.lang.String selectedDepartment, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> selectedSkills, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.ui.search.SortOrder sortOrder, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availableColleges, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availableDepartments, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availableSkills, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.User> results, @org.jetbrains.annotations.NotNull()
    java.util.List<com.skillswap.ai.data.model.User> allUsers, boolean isLoading, boolean hasSearched) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}