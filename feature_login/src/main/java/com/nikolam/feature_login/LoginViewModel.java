package com.nikolam.feature_login;

import android.net.Uri;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.nikolam.common.navigation.DeepLinks;
import com.nikolam.common.navigation.NavManager;
import com.nikolam.data.models.RegistrationResponse;
import com.nikolam.domain.LocalRegistrationUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import timber.log.Timber;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final NavManager navManager;
    private final LocalRegistrationUseCase localRegistration;

    @Inject
    public LoginViewModel(SavedStateHandle handle, NavManager navManager, LocalRegistrationUseCase localRegistration) {
        this.navManager = navManager;
        this.localRegistration = localRegistration;
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
        super.onCleared();
    }

    private final class LocalRegistrationObserver extends DisposableObserver<RegistrationResponse> {
        @Override
        public void onNext(@NonNull RegistrationResponse registrationResponse) {
            Timber.d("Success %s", registrationResponse.toString());
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
}
