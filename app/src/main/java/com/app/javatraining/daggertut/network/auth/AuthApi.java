package com.app.javatraining.daggertut.network.auth;

import com.app.javatraining.daggertut.models.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {
    @GET("users/{id}")
    Flowable<User> login(
            @Path("id") int id
    );
}
