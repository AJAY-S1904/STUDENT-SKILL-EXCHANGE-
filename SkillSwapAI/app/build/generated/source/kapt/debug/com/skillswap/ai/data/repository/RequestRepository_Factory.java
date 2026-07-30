package com.skillswap.ai.data.repository;

import com.google.firebase.database.FirebaseDatabase;
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
public final class RequestRepository_Factory implements Factory<RequestRepository> {
  private final Provider<FirebaseDatabase> databaseProvider;

  public RequestRepository_Factory(Provider<FirebaseDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RequestRepository get() {
    return newInstance(databaseProvider.get());
  }

  public static RequestRepository_Factory create(Provider<FirebaseDatabase> databaseProvider) {
    return new RequestRepository_Factory(databaseProvider);
  }

  public static RequestRepository newInstance(FirebaseDatabase database) {
    return new RequestRepository(database);
  }
}
