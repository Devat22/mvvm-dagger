package com.app.javatraining.daggertut.di.components;


import com.app.javatraining.daggertut.di.modules.AuthModule;
import com.app.javatraining.daggertut.di.modules.AuthViewModelsModule;
import com.app.javatraining.daggertut.ui.activities.auth.AuthActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent(modules = {
        AuthViewModelsModule.class,
        AuthModule.class
})
public interface AuthActivitySubcomponent extends AndroidInjector<AuthActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<AuthActivity> {}
}
