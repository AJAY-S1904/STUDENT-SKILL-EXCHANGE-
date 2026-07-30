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
public final class MeetingRepository_Factory implements Factory<MeetingRepository> {
  private final Provider<FirebaseDatabase> databaseProvider;

  public MeetingRepository_Factory(Provider<FirebaseDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public MeetingRepository get() {
    return newInstance(databaseProvider.get());
  }

  public static MeetingRepository_Factory create(Provider<FirebaseDatabase> databaseProvider) {
    return new MeetingRepository_Factory(databaseProvider);
  }

  public static MeetingRepository newInstance(FirebaseDatabase database) {
    return new MeetingRepository(database);
  }
}
