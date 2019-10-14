package com.app.javatraining.daggertut.ui.activities.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.app.javatraining.daggertut.models.User;
import com.app.javatraining.daggertut.network.auth.AuthApi;
import com.app.javatraining.daggertut.utils.Constants;
import com.app.javatraining.daggertut.utils.SessionManager;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private final AuthApi authApi;
    private SessionManager sessionManager;
    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();
    @Inject
    public AuthViewModel(AuthApi authApi,SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Log.d(Constants.MY_DEBUG_MESSAGE,"viewmodel is working....");

    }
    public void authApiWithId(int userid){
        Log.d(Constants.MY_DEBUG_MESSAGE,"Attempting to login");
        this.sessionManager.authWithid(queryId(userid));
    }
    private LiveData<AuthResource<User>> queryId(int userid){
        return LiveDataReactiveStreams
            .fromPublisher(authApi.login(userid)
                    .onErrorReturn(new Function<Throwable, User>() {
                        @Override
                        public User apply(Throwable throwable) throws Exception {
                            User errorUser = new User();
                            errorUser.setId(-1);
                            return errorUser;
                        }
                    })
                    .map(new Function<User, AuthResource<User>>() {
                        @Override
                        public AuthResource<User> apply(User user) throws Exception {
                            if(user.getId() == -1){
                                return AuthResource.error("Wrong credentials",null);
                            }
                            return AuthResource.authenticated("You are logged in",user);
                        }
                    })
                    .subscribeOn(Schedulers.io())
            );
    }
    public LiveData<AuthResource<User>> getUser(){
        return sessionManager.getUser();
    }

}
