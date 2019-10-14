package com.app.javatraining.daggertut.utils;

import com.app.javatraining.daggertut.di.components.AppComponent;
import com.app.javatraining.daggertut.di.components.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class Base extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent
                .builder()
                .application(this)
                .build();
    }
}
