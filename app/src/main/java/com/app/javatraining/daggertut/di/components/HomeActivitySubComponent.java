package com.app.javatraining.daggertut.di.components;

import com.app.javatraining.daggertut.di.modules.AuthModule;
import com.app.javatraining.daggertut.di.modules.AuthViewModelsModule;
import com.app.javatraining.daggertut.di.modules.HomeMainActivityModule;
import com.app.javatraining.daggertut.ui.activities.auth.AuthActivity;
import com.app.javatraining.daggertut.ui.activities.main.HomeMain;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
@Subcomponent(modules = {
        HomeMainActivityModule.class
})
public interface HomeActivitySubComponent extends AndroidInjector<HomeMain> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<HomeMain> {}
}
