package com.skillswap.ai.ui.skills;

import androidx.lifecycle.ViewModel;
import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0015\u001a\u00020\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u0017H\u0002J\u0016\u0010\u0018\u001a\u00020\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u0017H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/skillswap/ai/ui/skills/SkillViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/skillswap/ai/data/repository/AuthRepository;", "userRepository", "Lcom/skillswap/ai/data/repository/UserRepository;", "(Lcom/skillswap/ai/data/repository/AuthRepository;Lcom/skillswap/ai/data/repository/UserRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/skillswap/ai/ui/skills/SkillUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "addLearnSkill", "", "skill", "", "addTeachSkill", "removeLearnSkill", "removeTeachSkill", "saveLearnSkills", "skills", "", "saveTeachSkills", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SkillViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.skillswap.ai.ui.skills.SkillUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.skills.SkillUiState> uiState = null;
    
    @javax.inject.Inject()
    public SkillViewModel(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.skills.SkillUiState> getUiState() {
        return null;
    }
    
    public final void addTeachSkill(@org.jetbrains.annotations.NotNull()
    java.lang.String skill) {
    }
    
    public final void removeTeachSkill(@org.jetbrains.annotations.NotNull()
    java.lang.String skill) {
    }
    
    public final void addLearnSkill(@org.jetbrains.annotations.NotNull()
    java.lang.String skill) {
    }
    
    public final void removeLearnSkill(@org.jetbrains.annotations.NotNull()
    java.lang.String skill) {
    }
    
    private final void saveTeachSkills(java.util.List<java.lang.String> skills) {
    }
    
    private final void saveLearnSkills(java.util.List<java.lang.String> skills) {
    }
}