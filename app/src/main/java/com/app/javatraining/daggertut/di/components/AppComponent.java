package com.app.javatraining.daggertut.di.components;

import android.app.Application;

import com.app.javatraining.daggertut.di.modules.AppModule;
import com.app.javatraining.daggertut.di.modules.ActivityBuildersModule;
import com.app.javatraining.daggertut.di.modules.ViewModelFactoryModule;
import com.app.javatraining.daggertut.utils.Base;
import com.app.javatraining.daggertut.utils.SessionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuildersModule.class,
        ViewModelFactoryModule.class,
        AppModule.class
})
public interface AppComponent extends AndroidInjector<Base> {
    AuthActivitySubcomponent.Factory authActivitySubComponent();
    SessionManager sessionManager();
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
}
