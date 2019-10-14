package com.app.javatraining.daggertut.di.modules;

import com.app.javatraining.daggertut.di.components.AuthActivitySubcomponent;
import com.app.javatraining.daggertut.di.components.HomeActivitySubComponent;
import com.app.javatraining.daggertut.ui.activities.auth.AuthActivity;
import com.app.javatraining.daggertut.ui.activities.main.HomeMain;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class ActivityBuildersModule {

//    @ContributesAndroidInjector(
//            modules = {AuthViewModelsModule.class}
//    )
//    abstract AuthActivity authActivityClient();
@Binds
@IntoMap
@ClassKey(AuthActivity.class)
abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
        AuthActivitySubcomponent.Factory builder);
@Binds
@IntoMap
@ClassKey(HomeMain.class)
abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
        HomeActivitySubComponent.Factory builder);
}
