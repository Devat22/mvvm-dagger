package com.app.javatraining.daggertut.utils;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.app.javatraining.daggertut.models.User;
import com.app.javatraining.daggertut.ui.activities.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    private MediatorLiveData<AuthResource<User>> cashedUser = new MediatorLiveData<>();
    @Inject
    public SessionManager(){

    }
    public void authWithid(final LiveData<AuthResource<User>> source){
        if(cashedUser != null){
            cashedUser.setValue(AuthResource.loading((User)null));
            cashedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cashedUser.setValue(userAuthResource);
                    cashedUser.removeSource(source);
                }
            });
        }else {
            Log.d(Constants.MY_DEBUG_MESSAGE,"Previous session detected. Returning user from cach.");
        }
    }
    public void logout(){
        Log.d(Constants.MY_DEBUG_MESSAGE,"Logged out");
        cashedUser.setValue(AuthResource.<User>logout());
    }
    public LiveData<AuthResource<User>> getUser(){
        return cashedUser;
    }
}
