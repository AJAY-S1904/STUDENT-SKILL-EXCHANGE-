package com.skillswap.ai.ui.credits;

import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.CreditRepository;
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
public final class CreditViewModel_Factory implements Factory<CreditViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<CreditRepository> creditRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public CreditViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<CreditRepository> creditRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.creditRepositoryProvider = creditRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public CreditViewModel get() {
    return newInstance(authRepositoryProvider.get(), creditRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static CreditViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<CreditRepository> creditRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new CreditViewModel_Factory(authRepositoryProvider, creditRepositoryProvider, userRepositoryProvider);
  }

  public static CreditViewModel newInstance(AuthRepository authRepository,
      CreditRepository creditRepository, UserRepository userRepository) {
    return new CreditViewModel(authRepository, creditRepository, userRepository);
  }
}
