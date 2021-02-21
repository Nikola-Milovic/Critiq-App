package com.nikolam.feature_main.presentation;

import android.net.Uri;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.nikolam.common.navigation.DeepLinks;
import com.nikolam.common.navigation.NavManager;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class MainViewModel extends ViewModel {

    private final NavManager navManager;

    @Inject
    public MainViewModel(SavedStateHandle handle, NavManager navManager) {
        this.navManager = navManager;
    }

    void navigateToUpload() {
        Uri uri = Uri.parse(DeepLinks.UPLOAD_DEEPLINK);
        navManager.navigate(uri);
    }

    void navigateToMyPosts() {
        Uri uri = Uri.parse(DeepLinks.MY_POSTS_DEEPLINK);
        navManager.navigate(uri);
    }
}