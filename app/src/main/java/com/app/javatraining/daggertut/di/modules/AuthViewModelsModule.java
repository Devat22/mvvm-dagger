package com.app.javatraining.daggertut.di.modules;

import androidx.lifecycle.ViewModel;

import com.app.javatraining.daggertut.ui.activities.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
