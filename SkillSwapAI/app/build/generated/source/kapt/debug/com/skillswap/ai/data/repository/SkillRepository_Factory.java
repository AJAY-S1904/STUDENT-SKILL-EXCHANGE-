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
public final class SkillRepository_Factory implements Factory<SkillRepository> {
  private final Provider<FirebaseDatabase> databaseProvider;

  public SkillRepository_Factory(Provider<FirebaseDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SkillRepository get() {
    return newInstance(databaseProvider.get());
  }

  public static SkillRepository_Factory create(Provider<FirebaseDatabase> databaseProvider) {
    return new SkillRepository_Factory(databaseProvider);
  }

  public static SkillRepository newInstance(FirebaseDatabase database) {
    return new SkillRepository(database);
  }
}
