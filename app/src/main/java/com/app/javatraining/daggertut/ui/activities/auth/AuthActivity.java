package com.app.javatraining.daggertut.ui.activities.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.javatraining.daggertut.R;
import com.app.javatraining.daggertut.models.User;
import com.app.javatraining.daggertut.ui.activities.main.HomeMain;
import com.app.javatraining.daggertut.utils.Constants;
import com.app.javatraining.daggertut.viewmodels.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    Drawable logo;
    @Inject
    RequestManager requestManager;
    private AuthViewModel viewModel;
    EditText userid;
    Button login;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders
                .of(this,providerFactory)
                .get(AuthViewModel.class);
        setupLogo();
        subscribeObservers();
    }

    private void setupLogo() {
        userid = findViewById(R.id.user_id_input);
        login = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progress_bar);
        login.setOnClickListener(this);
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                attemptLogin();

                break;
                default:return;
        }
    }
    private void attemptLogin(){
        if(TextUtils.isEmpty(userid.getText().toString())){
            return;
        }
        viewModel.authApiWithId(Integer
        .parseInt(userid.getText().toString()));
    }
    private void subscribeObservers(){
        viewModel.getUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> user) {
                if (user != null){
                    switch (user.status){
                        case LOADING:
                            showProgressBar(true);
                            break;
                        case ERROR:
                            Log.d(Constants.MY_DEBUG_MESSAGE,"Error: "+user.message);
                            showProgressBar(false);
                            break;
                        case AUTHENTICATED:
                            Log.d(Constants.MY_DEBUG_MESSAGE,"Succuss: "+user.message);
                            showProgressBar(false);
                            logTheUserIn();
                            break;
                        case NOT_AUTHENTICATED:
                            showProgressBar(false);
                            break;
                            default:throw new NullPointerException("user is not returning any status");
                    }
                }
            }
        });
    }
    private void logTheUserIn(){
        startActivity(new Intent(this, HomeMain.class));
        finish();
    }
    private void showProgressBar(boolean isVisible){
        if (isVisible){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
