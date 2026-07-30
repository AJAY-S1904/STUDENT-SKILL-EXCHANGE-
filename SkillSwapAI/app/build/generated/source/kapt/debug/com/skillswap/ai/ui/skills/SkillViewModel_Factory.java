package com.skillswap.ai.ui.skills;

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
public final class SkillViewModel_Factory implements Factory<SkillViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public SkillViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public SkillViewModel get() {
    return newInstance(authRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static SkillViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new SkillViewModel_Factory(authRepositoryProvider, userRepositoryProvider);
  }

  public static SkillViewModel newInstance(AuthRepository authRepository,
      UserRepository userRepository) {
    return new SkillViewModel(authRepository, userRepository);
  }
}
