package com.nikolam.critiq;

import android.app.Application;
import org.jetbrains.annotations.NotNull;
import dagger.hilt.android.HiltAndroidApp;

import leakcanary.LeakCanary;
import timber.log.Timber;

@HiltAndroidApp
public class CritiqApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // This will initialise Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {


                @Override
                protected void log(int priority, String tag, @NotNull String message, Throwable t) {
                    super.log(priority, "critiq_$tag", message, t);
                }
            });
        }
    }
}
