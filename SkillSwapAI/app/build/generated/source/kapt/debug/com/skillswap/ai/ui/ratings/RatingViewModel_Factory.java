package com.skillswap.ai.ui.ratings;

import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.RatingRepository;
import com.skillswap.ai.data.repository.SessionRepository;
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
public final class RatingViewModel_Factory implements Factory<RatingViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<RatingRepository> ratingRepositoryProvider;

  private final Provider<SessionRepository> sessionRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public RatingViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<RatingRepository> ratingRepositoryProvider,
      Provider<SessionRepository> sessionRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.ratingRepositoryProvider = ratingRepositoryProvider;
    this.sessionRepositoryProvider = sessionRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public RatingViewModel get() {
    return newInstance(authRepositoryProvider.get(), ratingRepositoryProvider.get(), sessionRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static RatingViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<RatingRepository> ratingRepositoryProvider,
      Provider<SessionRepository> sessionRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new RatingViewModel_Factory(authRepositoryProvider, ratingRepositoryProvider, sessionRepositoryProvider, userRepositoryProvider);
  }

  public static RatingViewModel newInstance(AuthRepository authRepository,
      RatingRepository ratingRepository, SessionRepository sessionRepository,
      UserRepository userRepository) {
    return new RatingViewModel(authRepository, ratingRepository, sessionRepository, userRepository);
  }
}
