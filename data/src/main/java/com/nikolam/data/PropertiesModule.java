package com.nikolam.data;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module()
@InstallIn(SingletonComponent.class)
public class PropertiesModule {

    private static final String PREF_TAG = "PROPERTIES";
    private static final String USER_ID_KEY = "USERID";

    private String userId;

    @Provides
    @Named("userID")
    String provideUserID(@ApplicationContext Context context) {
        if (userId != null) {
            return userId;
        }
        SharedPreferences pref = context.getSharedPreferences(PREF_TAG, Context.MODE_PRIVATE);
        userId = pref.getString(USER_ID_KEY, "");
        return userId;
    }
}