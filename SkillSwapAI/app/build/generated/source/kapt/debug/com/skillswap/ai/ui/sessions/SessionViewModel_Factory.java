package com.skillswap.ai.ui.sessions;

import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.CreditRepository;
import com.skillswap.ai.data.repository.SessionRepository;
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
public final class SessionViewModel_Factory implements Factory<SessionViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<SessionRepository> sessionRepositoryProvider;

  private final Provider<CreditRepository> creditRepositoryProvider;

  public SessionViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<SessionRepository> sessionRepositoryProvider,
      Provider<CreditRepository> creditRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.sessionRepositoryProvider = sessionRepositoryProvider;
    this.creditRepositoryProvider = creditRepositoryProvider;
  }

  @Override
  public SessionViewModel get() {
    return newInstance(authRepositoryProvider.get(), sessionRepositoryProvider.get(), creditRepositoryProvider.get());
  }

  public static SessionViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<SessionRepository> sessionRepositoryProvider,
      Provider<CreditRepository> creditRepositoryProvider) {
    return new SessionViewModel_Factory(authRepositoryProvider, sessionRepositoryProvider, creditRepositoryProvider);
  }

  public static SessionViewModel newInstance(AuthRepository authRepository,
      SessionRepository sessionRepository, CreditRepository creditRepository) {
    return new SessionViewModel(authRepository, sessionRepository, creditRepository);
  }
}
