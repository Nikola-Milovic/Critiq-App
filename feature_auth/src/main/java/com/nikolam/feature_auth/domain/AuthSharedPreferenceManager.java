package com.nikolam.feature_auth.domain;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;
import timber.log.Timber;

@Singleton
public class AuthSharedPreferenceManager {
    private final SharedPreferences pref;
    private final SharedPreferences propertiesPref;

    private static final String PREF_TAG = "AUTH";
    private static final String TOKEN = "TOKEN";
    private static final String PERMALINK = "PERMALINK";

    private static final String PREF_TAG_PROPERTIES = "PROPERTIES";
    private static final String USER_ID_KEY = "USERID";

    @Inject
    public AuthSharedPreferenceManager(@ApplicationContext Context context) {
        this.pref = context.getSharedPreferences(PREF_TAG, Context.MODE_PRIVATE);
        this.propertiesPref = context.getSharedPreferences(PREF_TAG_PROPERTIES, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        Timber.d("Save Token %s", token);
        this.pref.edit().putString(TOKEN, token).apply();
    }

    public void savePermalink(String plink) {
        Timber.d("Save permalink %s", plink);
        this.pref.edit().putString(PERMALINK, plink).apply();
    }

    public void saveUserID(String id) {
        Timber.d("Save id %s", id);
        this.propertiesPref.edit().putString(USER_ID_KEY, id).apply();
    }

    public String getTokenOrNull() {
        return this.pref.getString(TOKEN, null);
    }

    public String getPermalink() {
        String permalink = this.pref.getString(PERMALINK, "");
        return permalink;
    }

}
