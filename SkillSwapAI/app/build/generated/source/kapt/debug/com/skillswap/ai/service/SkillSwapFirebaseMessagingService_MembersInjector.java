package com.skillswap.ai.service;

import com.skillswap.ai.data.repository.UserRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class SkillSwapFirebaseMessagingService_MembersInjector implements MembersInjector<SkillSwapFirebaseMessagingService> {
  private final Provider<UserRepository> userRepositoryProvider;

  public SkillSwapFirebaseMessagingService_MembersInjector(
      Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  public static MembersInjector<SkillSwapFirebaseMessagingService> create(
      Provider<UserRepository> userRepositoryProvider) {
    return new SkillSwapFirebaseMessagingService_MembersInjector(userRepositoryProvider);
  }

  @Override
  public void injectMembers(SkillSwapFirebaseMessagingService instance) {
    injectUserRepository(instance, userRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.skillswap.ai.service.SkillSwapFirebaseMessagingService.userRepository")
  public static void injectUserRepository(SkillSwapFirebaseMessagingService instance,
      UserRepository userRepository) {
    instance.userRepository = userRepository;
  }
}
