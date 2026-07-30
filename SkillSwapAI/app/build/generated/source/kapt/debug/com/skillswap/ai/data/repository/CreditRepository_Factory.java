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
public final class CreditRepository_Factory implements Factory<CreditRepository> {
  private final Provider<FirebaseDatabase> databaseProvider;

  public CreditRepository_Factory(Provider<FirebaseDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CreditRepository get() {
    return newInstance(databaseProvider.get());
  }

  public static CreditRepository_Factory create(Provider<FirebaseDatabase> databaseProvider) {
    return new CreditRepository_Factory(databaseProvider);
  }

  public static CreditRepository newInstance(FirebaseDatabase database) {
    return new CreditRepository(database);
  }
}
