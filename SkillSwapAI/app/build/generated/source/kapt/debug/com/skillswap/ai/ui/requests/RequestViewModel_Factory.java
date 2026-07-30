package com.skillswap.ai.ui.requests;

import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.CreditRepository;
import com.skillswap.ai.data.repository.MeetingRepository;
import com.skillswap.ai.data.repository.NotificationRepository;
import com.skillswap.ai.data.repository.RequestRepository;
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
public final class RequestViewModel_Factory implements Factory<RequestViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<RequestRepository> requestRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<NotificationRepository> notificationRepositoryProvider;

  private final Provider<CreditRepository> creditRepositoryProvider;

  private final Provider<SessionRepository> sessionRepositoryProvider;

  private final Provider<MeetingRepository> meetingRepositoryProvider;

  public RequestViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<RequestRepository> requestRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<NotificationRepository> notificationRepositoryProvider,
      Provider<CreditRepository> creditRepositoryProvider,
      Provider<SessionRepository> sessionRepositoryProvider,
      Provider<MeetingRepository> meetingRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.requestRepositoryProvider = requestRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
    this.notificationRepositoryProvider = notificationRepositoryProvider;
    this.creditRepositoryProvider = creditRepositoryProvider;
    this.sessionRepositoryProvider = sessionRepositoryProvider;
    this.meetingRepositoryProvider = meetingRepositoryProvider;
  }

  @Override
  public RequestViewModel get() {
    return newInstance(authRepositoryProvider.get(), requestRepositoryProvider.get(), userRepositoryProvider.get(), notificationRepositoryProvider.get(), creditRepositoryProvider.get(), sessionRepositoryProvider.get(), meetingRepositoryProvider.get());
  }

  public static RequestViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<RequestRepository> requestRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<NotificationRepository> notificationRepositoryProvider,
      Provider<CreditRepository> creditRepositoryProvider,
      Provider<SessionRepository> sessionRepositoryProvider,
      Provider<MeetingRepository> meetingRepositoryProvider) {
    return new RequestViewModel_Factory(authRepositoryProvider, requestRepositoryProvider, userRepositoryProvider, notificationRepositoryProvider, creditRepositoryProvider, sessionRepositoryProvider, meetingRepositoryProvider);
  }

  public static RequestViewModel newInstance(AuthRepository authRepository,
      RequestRepository requestRepository, UserRepository userRepository,
      NotificationRepository notificationRepository, CreditRepository creditRepository,
      SessionRepository sessionRepository, MeetingRepository meetingRepository) {
    return new RequestViewModel(authRepository, requestRepository, userRepository, notificationRepository, creditRepository, sessionRepository, meetingRepository);
  }
}
