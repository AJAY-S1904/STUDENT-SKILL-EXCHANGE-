package com.skillswap.ai.di;

import com.skillswap.ai.data.remote.AiApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class AppModule_ProvideAiApiServiceFactory implements Factory<AiApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public AppModule_ProvideAiApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public AiApiService get() {
    return provideAiApiService(retrofitProvider.get());
  }

  public static AppModule_ProvideAiApiServiceFactory create(Provider<Retrofit> retrofitProvider) {
    return new AppModule_ProvideAiApiServiceFactory(retrofitProvider);
  }

  public static AiApiService provideAiApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAiApiService(retrofit));
  }
}
