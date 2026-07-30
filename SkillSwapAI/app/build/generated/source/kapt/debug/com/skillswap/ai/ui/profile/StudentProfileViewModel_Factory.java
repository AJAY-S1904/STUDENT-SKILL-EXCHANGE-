package com.skillswap.ai.ui.profile;

import androidx.lifecycle.SavedStateHandle;
import com.skillswap.ai.data.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class StudentProfileViewModel_Factory implements Factory<StudentProfileViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public StudentProfileViewModel_Factory(Provider<UserRepository> userRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public StudentProfileViewModel get() {
    return newInstance(userRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static StudentProfileViewModel_Factory create(
      Provider<UserRepository> userRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new StudentProfileViewModel_Factory(userRepositoryProvider, savedStateHandleProvider);
  }

  public static StudentProfileViewModel newInstance(UserRepository userRepository,
      SavedStateHandle savedStateHandle) {
    return new StudentProfileViewModel(userRepository, savedStateHandle);
  }
}
