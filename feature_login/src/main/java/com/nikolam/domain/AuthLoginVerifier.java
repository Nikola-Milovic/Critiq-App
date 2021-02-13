package com.nikolam.domain;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;
import timber.log.Timber;

@Singleton
public class AuthLoginVerifier {
    private final SharedPreferences pref;

    private static final String PREF_TAG = "AUTH";
    private static final String TOKEN = "TOKEN";
    private static final String PERMALINK = "PERMALINK";

    @Inject
    public AuthLoginVerifier(@ApplicationContext Context context) {
        this.pref = context.getSharedPreferences(PREF_TAG, Context.MODE_PRIVATE);
    }

    public void saveToken(String token){
        Timber.d("Save Token %s", token);
        this.pref.edit().putString(TOKEN, token).apply();
    }

    public void savePermalink(String plink){
        Timber.d("Save permalink %s", plink);
        this.pref.edit().putString(PERMALINK, plink).apply();
    }

    public String getTokenOrNull(){
        return this.pref.getString(TOKEN, null);
    }

    public String getPermalink(){
        String permalink = this.pref.getString(PERMALINK, null);
        assert permalink != null;
        return permalink;
    }

}
