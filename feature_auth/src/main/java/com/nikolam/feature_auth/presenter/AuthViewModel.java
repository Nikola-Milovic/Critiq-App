package com.nikolam.feature_auth.presenter;

import android.net.Uri;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.nikolam.common.navigation.DeepLinks;
import com.nikolam.common.navigation.NavManager;
import com.nikolam.feature_auth.data.models.LoginResponse;
import com.nikolam.feature_auth.data.models.RegistrationResponse;
import com.nikolam.feature_auth.domain.AuthLoginVerifier;
import com.nikolam.feature_auth.domain.LocalRegistrationUseCase;
import com.nikolam.feature_auth.domain.TokenLoginUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import timber.log.Timber;

@HiltViewModel
public class AuthViewModel extends ViewModel {

    private final NavManager navManager;
    private final LocalRegistrationUseCase localRegistration;
    private final TokenLoginUseCase tokenLogin;
    private final AuthLoginVerifier verifier;

    @Inject
    public AuthViewModel(SavedStateHandle handle, NavManager navManager, LocalRegistrationUseCase localRegistration,
                         TokenLoginUseCase tokenLogin,
                         AuthLoginVerifier verifier) {
        this.navManager = navManager;
        this.localRegistration = localRegistration;
        this.verifier = verifier;
        this.tokenLogin = tokenLogin;
    }


    public void checkSession() {
        String permalink = verifier.getPermalink();

        if (permalink.isEmpty()) {
            Timber.d("permalink empty");
            // go to register
        } else {
            //check if token expired, if it did go to login else login
            String token = verifier.getTokenOrNull();
            tokenLogin.execute(new TokenLoginObserver(), new TokenLoginUseCase.Params(token, permalink));
        }


    }

    public void localRegistration(String username, String email, String password) {
        localRegistration.execute(new LocalRegistrationObserver(), new LocalRegistrationUseCase.Params(email, username, password));
    }

    void navigateToMainScreen() {
        if (navManager == null) {
            Timber.d("is Null");
            return;
        }
        Uri uri = Uri.parse(DeepLinks.MAIN_SCREEN_DEEPLINK);
        navManager.navigate(uri);
    }

    @Override
    protected void onCleared() {
        this.localRegistration.dispose();
        this.tokenLogin.dispose();
        super.onCleared();
    }

    private final class LocalRegistrationObserver extends DisposableObserver<RegistrationResponse> {
        @Override
        public void onNext(@NonNull RegistrationResponse registrationResponse) {
            verifier.savePermalink(registrationResponse.getPermalink());
            verifier.saveToken(registrationResponse.getToken());
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e(e);
        }

        @Override
        public void onComplete() {
            Timber.d("Complete");
            navigateToMainScreen();
        }
    }

    private final class TokenLoginObserver extends DisposableObserver<LoginResponse> {
        @Override
        public void onNext(@NonNull LoginResponse loginResponse) {
            if(loginResponse.getStatus() == 200){
                navigateToMainScreen();
            } else {
                Timber.d("Login attempt was unsuccessful %s", loginResponse.toString());
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e(e);
        }

        @Override
        public void onComplete() {
            Timber.d("Complete token login");
        }
    }
}
