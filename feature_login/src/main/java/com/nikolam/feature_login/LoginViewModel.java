package com.nikolam.feature_login;

import android.net.Uri;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.nikolam.common.navigation.DeepLinks;
import com.nikolam.common.navigation.NavManager;
import com.nikolam.data.models.RegistrationModel;
import com.nikolam.domain.AuthRepository;

import javax.inject.Inject;

import dagger.assisted.Assisted;
import dagger.hilt.android.lifecycle.HiltViewModel;
import timber.log.Timber;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final NavManager navManager;
    private final AuthRepository repository;

    @Inject
    public LoginViewModel(SavedStateHandle handle, NavManager navManager, AuthRepository repository){
        this.navManager = navManager;
        this.repository = repository;
    }


    public void localRegistration(String username, String email, String password){
        repository.localRegistration(new RegistrationModel(email, username, password));
    }

    void navigateToMainScreen() {
        if (navManager == null){
            Timber.d("is Null");
            return;
        }
        Uri uri = Uri.parse(DeepLinks.MAIN_SCREEN_DEEPLINK);
        navManager.navigate(uri);
    }
}