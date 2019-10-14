package com.app.javatraining.daggertut.ui.activities.main;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.app.javatraining.daggertut.R;
import com.app.javatraining.daggertut.ui.activities.home.Home;

public class HomeMain extends Home {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
    }
}
