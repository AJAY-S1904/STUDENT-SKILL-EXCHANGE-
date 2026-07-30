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
public final class SessionRepository_Factory implements Factory<SessionRepository> {
  private final Provider<FirebaseDatabase> databaseProvider;

  public SessionRepository_Factory(Provider<FirebaseDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SessionRepository get() {
    return newInstance(databaseProvider.get());
  }

  public static SessionRepository_Factory create(Provider<FirebaseDatabase> databaseProvider) {
    return new SessionRepository_Factory(databaseProvider);
  }

  public static SessionRepository newInstance(FirebaseDatabase database) {
    return new SessionRepository(database);
  }
}
