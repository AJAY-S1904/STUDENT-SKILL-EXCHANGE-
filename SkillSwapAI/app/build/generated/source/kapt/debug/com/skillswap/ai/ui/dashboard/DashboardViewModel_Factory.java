package com.skillswap.ai.ui.dashboard;

import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.NotificationRepository;
import com.skillswap.ai.data.repository.RequestRepository;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<RequestRepository> requestRepositoryProvider;

  private final Provider<NotificationRepository> notificationRepositoryProvider;

  public DashboardViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<RequestRepository> requestRepositoryProvider,
      Provider<NotificationRepository> notificationRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
    this.requestRepositoryProvider = requestRepositoryProvider;
    this.notificationRepositoryProvider = notificationRepositoryProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(authRepositoryProvider.get(), userRepositoryProvider.get(), requestRepositoryProvider.get(), notificationRepositoryProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<RequestRepository> requestRepositoryProvider,
      Provider<NotificationRepository> notificationRepositoryProvider) {
    return new DashboardViewModel_Factory(authRepositoryProvider, userRepositoryProvider, requestRepositoryProvider, notificationRepositoryProvider);
  }

  public static DashboardViewModel newInstance(AuthRepository authRepository,
      UserRepository userRepository, RequestRepository requestRepository,
      NotificationRepository notificationRepository) {
    return new DashboardViewModel(authRepository, userRepository, requestRepository, notificationRepository);
  }
}
