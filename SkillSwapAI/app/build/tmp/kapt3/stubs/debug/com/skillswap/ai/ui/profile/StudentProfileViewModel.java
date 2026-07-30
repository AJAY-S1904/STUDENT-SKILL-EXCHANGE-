package com.skillswap.ai.ui.profile;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.skillswap.ai.data.model.User;
import com.skillswap.ai.data.repository.AuthResult;
import com.skillswap.ai.data.repository.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0010\u001a\u00020\u0011H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/skillswap/ai/ui/profile/StudentProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "userRepository", "Lcom/skillswap/ai/data/repository/UserRepository;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/skillswap/ai/data/repository/UserRepository;Landroidx/lifecycle/SavedStateHandle;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/skillswap/ai/ui/profile/StudentProfileUiState;", "studentId", "", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadStudent", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class StudentProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.skillswap.ai.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.skillswap.ai.ui.profile.StudentProfileUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.profile.StudentProfileUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String studentId = null;
    
    @javax.inject.Inject()
    public StudentProfileViewModel(@org.jetbrains.annotations.NotNull()
    com.skillswap.ai.data.repository.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.skillswap.ai.ui.profile.StudentProfileUiState> getUiState() {
        return null;
    }
    
    private final void loadStudent() {
    }
}