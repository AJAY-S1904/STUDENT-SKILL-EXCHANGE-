package com.skillswap.ai.ui.matching;

import com.skillswap.ai.data.repository.AiRepository;
import com.skillswap.ai.data.repository.AuthRepository;
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
public final class AiMatchingViewModel_Factory implements Factory<AiMatchingViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<AiRepository> aiRepositoryProvider;

  public AiMatchingViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<AiRepository> aiRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
    this.aiRepositoryProvider = aiRepositoryProvider;
  }

  @Override
  public AiMatchingViewModel get() {
    return newInstance(authRepositoryProvider.get(), userRepositoryProvider.get(), aiRepositoryProvider.get());
  }

  public static AiMatchingViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<AiRepository> aiRepositoryProvider) {
    return new AiMatchingViewModel_Factory(authRepositoryProvider, userRepositoryProvider, aiRepositoryProvider);
  }

  public static AiMatchingViewModel newInstance(AuthRepository authRepository,
      UserRepository userRepository, AiRepository aiRepository) {
    return new AiMatchingViewModel(authRepository, userRepository, aiRepository);
  }
}
