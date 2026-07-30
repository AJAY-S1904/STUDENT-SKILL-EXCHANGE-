package com.skillswap.ai.data.repository;

import com.skillswap.ai.data.remote.AiApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AiRepository_Factory implements Factory<AiRepository> {
  private final Provider<AiApiService> aiApiServiceProvider;

  public AiRepository_Factory(Provider<AiApiService> aiApiServiceProvider) {
    this.aiApiServiceProvider = aiApiServiceProvider;
  }

  @Override
  public AiRepository get() {
    return newInstance(aiApiServiceProvider.get());
  }

  public static AiRepository_Factory create(Provider<AiApiService> aiApiServiceProvider) {
    return new AiRepository_Factory(aiApiServiceProvider);
  }

  public static AiRepository newInstance(AiApiService aiApiService) {
    return new AiRepository(aiApiService);
  }
}
