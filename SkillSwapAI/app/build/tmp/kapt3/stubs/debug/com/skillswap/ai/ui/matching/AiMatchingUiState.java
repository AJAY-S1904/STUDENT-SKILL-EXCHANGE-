package com.skillswap.ai.ui.matching;

import androidx.lifecycle.ViewModel;
import com.skillswap.ai.data.model.AiMatchRequest;
import com.skillswap.ai.data.model.AiMatchResponse;
import com.skillswap.ai.data.model.CandidateProfile;
import com.skillswap.ai.data.repository.AiRepository;
import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.AuthResult;
import com.skillswap.ai.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0089\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0012J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u000fH\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u00c6\u0003J\u000f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\nH\u00c6\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\t\u0010,\u001a\u00020\u000fH\u00c6\u0003J\u008d\u0001\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u000fH\u00c6\u0001J\u0013\u0010.\u001a\u00020\u000f2\b\u0010/\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00100\u001a\u000201H\u00d6\u0001J\t\u00102\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u001dR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016\u00a8\u00063"}, d2 = {"Lcom/skillswap/ai/ui/matching/AiMatchingUiState;", "", "currentUserName", "", "currentDepartment", "teachSkills", "", "learnSkills", "experience", "rating", "", "availability", "aiResponse", "Lcom/skillswap/ai/data/model/AiMatchResponse;", "isLoading", "", "error", "hasMatched", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;DLjava/util/List;Lcom/skillswap/ai/data/model/AiMatchResponse;ZLjava/lang/String;Z)V", "getAiResponse", "()Lcom/skillswap/ai/data/model/AiMatchResponse;", "getAvailability", "()Ljava/util/List;", "getCurrentDepartment", "()Ljava/lang/String;", "getCurrentUserName", "getError", "getExperience", "getHasMatched", "()Z", "getLearnSkills", "getRating", "()D", "getTeachSkills", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class AiMatchingUiState {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String currentUserName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String currentDepartment = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> teachSkills = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> learnSkills = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String experience = null;
    private final double rating = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> availability = null;
    @org.jetbrains.annotations.Nullable()
    private final com.skillswap.ai.data.model.AiMatchResponse aiResponse = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    private final boolean hasMatched = false;
    
    public AiMatchingUiState(@org.jetbrains.annotations.NotNull()
    java.lang.String currentUserName, @org.jetbrains.annotations.NotNull()
    java.lang.String currentDepartment, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> teachSkills, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> learnSkills, @org.jetbrains.annotations.NotNull()
    java.lang.String experience, double rating, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availability, @org.jetbrains.annotations.Nullable()
    com.skillswap.ai.data.model.AiMatchResponse aiResponse, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean hasMatched) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentUserName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentDepartment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTeachSkills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getLearnSkills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExperience() {
        return null;
    }
    
    public final double getRating() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getAvailability() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.skillswap.ai.data.model.AiMatchResponse getAiResponse() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public final boolean getHasMatched() {
        return false;
    }
    
    public AiMatchingUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    public final boolean component11() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.skillswap.ai.data.model.AiMatchResponse component8() {
        return null;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.skillswap.ai.ui.matching.AiMatchingUiState copy(@org.jetbrains.annotations.NotNull()
    java.lang.String currentUserName, @org.jetbrains.annotations.NotNull()
    java.lang.String currentDepartment, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> teachSkills, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> learnSkills, @org.jetbrains.annotations.NotNull()
    java.lang.String experience, double rating, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> availability, @org.jetbrains.annotations.Nullable()
    com.skillswap.ai.data.model.AiMatchResponse aiResponse, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean hasMatched) {
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