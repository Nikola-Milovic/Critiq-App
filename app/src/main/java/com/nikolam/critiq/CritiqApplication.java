package com.nikolam.critiq;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class CritiqApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
