package com.skillswap.ai.ui.search;

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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public SearchViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(authRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static SearchViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new SearchViewModel_Factory(authRepositoryProvider, userRepositoryProvider);
  }

  public static SearchViewModel newInstance(AuthRepository authRepository,
      UserRepository userRepository) {
    return new SearchViewModel(authRepository, userRepository);
  }
}
