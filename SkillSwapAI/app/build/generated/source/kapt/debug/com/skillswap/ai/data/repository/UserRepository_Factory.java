package com.skillswap.ai.data.repository;

import android.content.Context;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class UserRepository_Factory implements Factory<UserRepository> {
  private final Provider<Context> contextProvider;

  private final Provider<FirebaseDatabase> databaseProvider;

  private final Provider<FirebaseStorage> storageProvider;

  public UserRepository_Factory(Provider<Context> contextProvider,
      Provider<FirebaseDatabase> databaseProvider, Provider<FirebaseStorage> storageProvider) {
    this.contextProvider = contextProvider;
    this.databaseProvider = databaseProvider;
    this.storageProvider = storageProvider;
  }

  @Override
  public UserRepository get() {
    return newInstance(contextProvider.get(), databaseProvider.get(), storageProvider.get());
  }

  public static UserRepository_Factory create(Provider<Context> contextProvider,
      Provider<FirebaseDatabase> databaseProvider, Provider<FirebaseStorage> storageProvider) {
    return new UserRepository_Factory(contextProvider, databaseProvider, storageProvider);
  }

  public static UserRepository newInstance(Context context, FirebaseDatabase database,
      FirebaseStorage storage) {
    return new UserRepository(context, database, storage);
  }
}
