package com.skillswap.ai.ui.notifications;

import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.NotificationRepository;
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
public final class NotificationViewModel_Factory implements Factory<NotificationViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<NotificationRepository> notificationRepositoryProvider;

  public NotificationViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<NotificationRepository> notificationRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.notificationRepositoryProvider = notificationRepositoryProvider;
  }

  @Override
  public NotificationViewModel get() {
    return newInstance(authRepositoryProvider.get(), notificationRepositoryProvider.get());
  }

  public static NotificationViewModel_Factory create(
      Provider<AuthRepository> authRepositoryProvider,
      Provider<NotificationRepository> notificationRepositoryProvider) {
    return new NotificationViewModel_Factory(authRepositoryProvider, notificationRepositoryProvider);
  }

  public static NotificationViewModel newInstance(AuthRepository authRepository,
      NotificationRepository notificationRepository) {
    return new NotificationViewModel(authRepository, notificationRepository);
  }
}
