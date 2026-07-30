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
public final class RatingRepository_Factory implements Factory<RatingRepository> {
  private final Provider<FirebaseDatabase> databaseProvider;

  public RatingRepository_Factory(Provider<FirebaseDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RatingRepository get() {
    return newInstance(databaseProvider.get());
  }

  public static RatingRepository_Factory create(Provider<FirebaseDatabase> databaseProvider) {
    return new RatingRepository_Factory(databaseProvider);
  }

  public static RatingRepository newInstance(FirebaseDatabase database) {
    return new RatingRepository(database);
  }
}
