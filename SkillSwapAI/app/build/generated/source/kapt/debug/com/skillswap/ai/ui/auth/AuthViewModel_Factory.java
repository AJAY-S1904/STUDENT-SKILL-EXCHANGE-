package com.skillswap.ai.ui.auth;

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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public AuthViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(authRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new AuthViewModel_Factory(authRepositoryProvider, userRepositoryProvider);
  }

  public static AuthViewModel newInstance(AuthRepository authRepository,
      UserRepository userRepository) {
    return new AuthViewModel(authRepository, userRepository);
  }
}
