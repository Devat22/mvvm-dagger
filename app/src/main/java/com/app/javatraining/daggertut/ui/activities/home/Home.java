package com.app.javatraining.daggertut.ui.activities.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.app.javatraining.daggertut.models.User;
import com.app.javatraining.daggertut.ui.activities.auth.AuthActivity;
import com.app.javatraining.daggertut.ui.activities.auth.AuthResource;
import com.app.javatraining.daggertut.utils.SessionManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class Home extends DaggerAppCompatActivity {
    @Inject
    SessionManager sessionManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void subscribe(){
        sessionManager.getUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null){
                    switch (userAuthResource.status){
                        case NOT_AUTHENTICATED:
                            logUserOut();
                            break;
                    }
                }
            }
        });
    }

    private void logUserOut() {
        startActivity(new Intent(this, AuthActivity.class));
    }
}
