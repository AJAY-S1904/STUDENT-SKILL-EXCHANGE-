package com.skillswap.ai;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.skillswap.ai.data.remote.AiApiService;
import com.skillswap.ai.data.repository.AiRepository;
import com.skillswap.ai.data.repository.AuthRepository;
import com.skillswap.ai.data.repository.CreditRepository;
import com.skillswap.ai.data.repository.MeetingRepository;
import com.skillswap.ai.data.repository.NotificationRepository;
import com.skillswap.ai.data.repository.RatingRepository;
import com.skillswap.ai.data.repository.RequestRepository;
import com.skillswap.ai.data.repository.SessionRepository;
import com.skillswap.ai.data.repository.UserRepository;
import com.skillswap.ai.di.AppModule_ProvideAiApiServiceFactory;
import com.skillswap.ai.di.AppModule_ProvideFirebaseAuthFactory;
import com.skillswap.ai.di.AppModule_ProvideFirebaseDatabaseFactory;
import com.skillswap.ai.di.AppModule_ProvideFirebaseStorageFactory;
import com.skillswap.ai.di.AppModule_ProvideOkHttpClientFactory;
import com.skillswap.ai.di.AppModule_ProvideRetrofitFactory;
import com.skillswap.ai.service.SkillSwapFirebaseMessagingService;
import com.skillswap.ai.service.SkillSwapFirebaseMessagingService_MembersInjector;
import com.skillswap.ai.ui.auth.AuthViewModel;
import com.skillswap.ai.ui.auth.AuthViewModel_HiltModules_KeyModule_ProvideFactory;
import com.skillswap.ai.ui.credits.CreditViewModel;
import com.skillswap.ai.ui.credits.CreditViewModel_HiltModules_KeyModule_ProvideFactory;
import com.skillswap.ai.ui.dashboard.DashboardViewModel;
import com.skillswap.ai.ui.dashboard.DashboardViewModel_HiltModules_KeyModule_ProvideFactory;
import com.skillswap.ai.ui.matching.AiMatchingViewModel;
import com.skillswap.ai.ui.matching.AiMatchingViewModel_HiltModules_KeyModule_ProvideFactory;
import com.skillswap.ai.ui.notifications.NotificationViewModel;
import com.skillswap.ai.ui.notifications.NotificationViewModel_HiltModules_KeyModule_ProvideFactory;
import com.skillswap.ai.ui.profile.ProfileViewModel;
import com.skillswap.ai.ui.profile.ProfileViewModel_HiltModules_KeyModule_ProvideFactory;
import com.skillswap.ai.ui.profile.StudentProfileViewModel;
import com.skillswap.ai.ui.profile.StudentProfileViewModel_HiltModules_KeyModule_ProvideFactory;
import com.skillswap.ai.ui.ratings.RatingViewModel;
import com.skillswap.ai.ui.ratings.RatingViewModel_HiltModules_KeyModule_ProvideFactory;
import com.skillswap.ai.ui.requests.RequestViewModel;
import com.skillswap.ai.ui.requests.RequestViewModel_HiltModules_KeyModule_ProvideFactory;
import com.skillswap.ai.ui.search.SearchViewModel;
import com.skillswap.ai.ui.search.SearchViewModel_HiltModules_KeyModule_ProvideFactory;
import com.skillswap.ai.ui.sessions.SessionViewModel;
import com.skillswap.ai.ui.sessions.SessionViewModel_HiltModules_KeyModule_ProvideFactory;
import com.skillswap.ai.ui.skills.SkillViewModel;
import com.skillswap.ai.ui.skills.SkillViewModel_HiltModules_KeyModule_ProvideFactory;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerSkillSwapApp_HiltComponents_SingletonC {
  private DaggerSkillSwapApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public SkillSwapApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements SkillSwapApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public SkillSwapApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements SkillSwapApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public SkillSwapApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements SkillSwapApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public SkillSwapApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements SkillSwapApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public SkillSwapApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements SkillSwapApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public SkillSwapApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements SkillSwapApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public SkillSwapApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements SkillSwapApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public SkillSwapApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends SkillSwapApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends SkillSwapApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends SkillSwapApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends SkillSwapApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return ImmutableSet.<String>of(AiMatchingViewModel_HiltModules_KeyModule_ProvideFactory.provide(), AuthViewModel_HiltModules_KeyModule_ProvideFactory.provide(), CreditViewModel_HiltModules_KeyModule_ProvideFactory.provide(), DashboardViewModel_HiltModules_KeyModule_ProvideFactory.provide(), NotificationViewModel_HiltModules_KeyModule_ProvideFactory.provide(), ProfileViewModel_HiltModules_KeyModule_ProvideFactory.provide(), RatingViewModel_HiltModules_KeyModule_ProvideFactory.provide(), RequestViewModel_HiltModules_KeyModule_ProvideFactory.provide(), SearchViewModel_HiltModules_KeyModule_ProvideFactory.provide(), SessionViewModel_HiltModules_KeyModule_ProvideFactory.provide(), SkillViewModel_HiltModules_KeyModule_ProvideFactory.provide(), StudentProfileViewModel_HiltModules_KeyModule_ProvideFactory.provide());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends SkillSwapApp_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AiMatchingViewModel> aiMatchingViewModelProvider;

    private Provider<AuthViewModel> authViewModelProvider;

    private Provider<CreditViewModel> creditViewModelProvider;

    private Provider<DashboardViewModel> dashboardViewModelProvider;

    private Provider<NotificationViewModel> notificationViewModelProvider;

    private Provider<ProfileViewModel> profileViewModelProvider;

    private Provider<RatingViewModel> ratingViewModelProvider;

    private Provider<RequestViewModel> requestViewModelProvider;

    private Provider<SearchViewModel> searchViewModelProvider;

    private Provider<SessionViewModel> sessionViewModelProvider;

    private Provider<SkillViewModel> skillViewModelProvider;

    private Provider<StudentProfileViewModel> studentProfileViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.aiMatchingViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.authViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.creditViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.dashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.notificationViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.ratingViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.requestViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.searchViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.sessionViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.skillViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
      this.studentProfileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 11);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(12).put("com.skillswap.ai.ui.matching.AiMatchingViewModel", ((Provider) aiMatchingViewModelProvider)).put("com.skillswap.ai.ui.auth.AuthViewModel", ((Provider) authViewModelProvider)).put("com.skillswap.ai.ui.credits.CreditViewModel", ((Provider) creditViewModelProvider)).put("com.skillswap.ai.ui.dashboard.DashboardViewModel", ((Provider) dashboardViewModelProvider)).put("com.skillswap.ai.ui.notifications.NotificationViewModel", ((Provider) notificationViewModelProvider)).put("com.skillswap.ai.ui.profile.ProfileViewModel", ((Provider) profileViewModelProvider)).put("com.skillswap.ai.ui.ratings.RatingViewModel", ((Provider) ratingViewModelProvider)).put("com.skillswap.ai.ui.requests.RequestViewModel", ((Provider) requestViewModelProvider)).put("com.skillswap.ai.ui.search.SearchViewModel", ((Provider) searchViewModelProvider)).put("com.skillswap.ai.ui.sessions.SessionViewModel", ((Provider) sessionViewModelProvider)).put("com.skillswap.ai.ui.skills.SkillViewModel", ((Provider) skillViewModelProvider)).put("com.skillswap.ai.ui.profile.StudentProfileViewModel", ((Provider) studentProfileViewModelProvider)).build();
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<String, Object>of();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.skillswap.ai.ui.matching.AiMatchingViewModel 
          return (T) new AiMatchingViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get(), singletonCImpl.aiRepositoryProvider.get());

          case 1: // com.skillswap.ai.ui.auth.AuthViewModel 
          return (T) new AuthViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get());

          case 2: // com.skillswap.ai.ui.credits.CreditViewModel 
          return (T) new CreditViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.creditRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get());

          case 3: // com.skillswap.ai.ui.dashboard.DashboardViewModel 
          return (T) new DashboardViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get(), singletonCImpl.requestRepositoryProvider.get(), singletonCImpl.notificationRepositoryProvider.get());

          case 4: // com.skillswap.ai.ui.notifications.NotificationViewModel 
          return (T) new NotificationViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.notificationRepositoryProvider.get());

          case 5: // com.skillswap.ai.ui.profile.ProfileViewModel 
          return (T) new ProfileViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get());

          case 6: // com.skillswap.ai.ui.ratings.RatingViewModel 
          return (T) new RatingViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.ratingRepositoryProvider.get(), singletonCImpl.sessionRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get());

          case 7: // com.skillswap.ai.ui.requests.RequestViewModel 
          return (T) new RequestViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.requestRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get(), singletonCImpl.notificationRepositoryProvider.get(), singletonCImpl.creditRepositoryProvider.get(), singletonCImpl.sessionRepositoryProvider.get(), singletonCImpl.meetingRepositoryProvider.get());

          case 8: // com.skillswap.ai.ui.search.SearchViewModel 
          return (T) new SearchViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get());

          case 9: // com.skillswap.ai.ui.sessions.SessionViewModel 
          return (T) new SessionViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.sessionRepositoryProvider.get(), singletonCImpl.creditRepositoryProvider.get());

          case 10: // com.skillswap.ai.ui.skills.SkillViewModel 
          return (T) new SkillViewModel(singletonCImpl.authRepositoryProvider.get(), singletonCImpl.userRepositoryProvider.get());

          case 11: // com.skillswap.ai.ui.profile.StudentProfileViewModel 
          return (T) new StudentProfileViewModel(singletonCImpl.userRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends SkillSwapApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends SkillSwapApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    @Override
    public void injectSkillSwapFirebaseMessagingService(SkillSwapFirebaseMessagingService arg0) {
      injectSkillSwapFirebaseMessagingService2(arg0);
    }

    @CanIgnoreReturnValue
    private SkillSwapFirebaseMessagingService injectSkillSwapFirebaseMessagingService2(
        SkillSwapFirebaseMessagingService instance) {
      SkillSwapFirebaseMessagingService_MembersInjector.injectUserRepository(instance, singletonCImpl.userRepositoryProvider.get());
      return instance;
    }
  }

  private static final class SingletonCImpl extends SkillSwapApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<FirebaseAuth> provideFirebaseAuthProvider;

    private Provider<AuthRepository> authRepositoryProvider;

    private Provider<FirebaseDatabase> provideFirebaseDatabaseProvider;

    private Provider<FirebaseStorage> provideFirebaseStorageProvider;

    private Provider<UserRepository> userRepositoryProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<AiApiService> provideAiApiServiceProvider;

    private Provider<AiRepository> aiRepositoryProvider;

    private Provider<CreditRepository> creditRepositoryProvider;

    private Provider<RequestRepository> requestRepositoryProvider;

    private Provider<NotificationRepository> notificationRepositoryProvider;

    private Provider<RatingRepository> ratingRepositoryProvider;

    private Provider<SessionRepository> sessionRepositoryProvider;

    private Provider<MeetingRepository> meetingRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideFirebaseAuthProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseAuth>(singletonCImpl, 1));
      this.authRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AuthRepository>(singletonCImpl, 0));
      this.provideFirebaseDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseDatabase>(singletonCImpl, 3));
      this.provideFirebaseStorageProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseStorage>(singletonCImpl, 4));
      this.userRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<UserRepository>(singletonCImpl, 2));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 8));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 7));
      this.provideAiApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<AiApiService>(singletonCImpl, 6));
      this.aiRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AiRepository>(singletonCImpl, 5));
      this.creditRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<CreditRepository>(singletonCImpl, 9));
      this.requestRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<RequestRepository>(singletonCImpl, 10));
      this.notificationRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<NotificationRepository>(singletonCImpl, 11));
      this.ratingRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<RatingRepository>(singletonCImpl, 12));
      this.sessionRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<SessionRepository>(singletonCImpl, 13));
      this.meetingRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<MeetingRepository>(singletonCImpl, 14));
    }

    @Override
    public void injectSkillSwapApp(SkillSwapApp skillSwapApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.skillswap.ai.data.repository.AuthRepository 
          return (T) new AuthRepository(singletonCImpl.provideFirebaseAuthProvider.get());

          case 1: // com.google.firebase.auth.FirebaseAuth 
          return (T) AppModule_ProvideFirebaseAuthFactory.provideFirebaseAuth();

          case 2: // com.skillswap.ai.data.repository.UserRepository 
          return (T) new UserRepository(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideFirebaseDatabaseProvider.get(), singletonCImpl.provideFirebaseStorageProvider.get());

          case 3: // com.google.firebase.database.FirebaseDatabase 
          return (T) AppModule_ProvideFirebaseDatabaseFactory.provideFirebaseDatabase();

          case 4: // com.google.firebase.storage.FirebaseStorage 
          return (T) AppModule_ProvideFirebaseStorageFactory.provideFirebaseStorage();

          case 5: // com.skillswap.ai.data.repository.AiRepository 
          return (T) new AiRepository(singletonCImpl.provideAiApiServiceProvider.get());

          case 6: // com.skillswap.ai.data.remote.AiApiService 
          return (T) AppModule_ProvideAiApiServiceFactory.provideAiApiService(singletonCImpl.provideRetrofitProvider.get());

          case 7: // retrofit2.Retrofit 
          return (T) AppModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 8: // okhttp3.OkHttpClient 
          return (T) AppModule_ProvideOkHttpClientFactory.provideOkHttpClient();

          case 9: // com.skillswap.ai.data.repository.CreditRepository 
          return (T) new CreditRepository(singletonCImpl.provideFirebaseDatabaseProvider.get());

          case 10: // com.skillswap.ai.data.repository.RequestRepository 
          return (T) new RequestRepository(singletonCImpl.provideFirebaseDatabaseProvider.get());

          case 11: // com.skillswap.ai.data.repository.NotificationRepository 
          return (T) new NotificationRepository(singletonCImpl.provideFirebaseDatabaseProvider.get());

          case 12: // com.skillswap.ai.data.repository.RatingRepository 
          return (T) new RatingRepository(singletonCImpl.provideFirebaseDatabaseProvider.get());

          case 13: // com.skillswap.ai.data.repository.SessionRepository 
          return (T) new SessionRepository(singletonCImpl.provideFirebaseDatabaseProvider.get());

          case 14: // com.skillswap.ai.data.repository.MeetingRepository 
          return (T) new MeetingRepository(singletonCImpl.provideFirebaseDatabaseProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
