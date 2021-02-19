package com.nikolam.feature_auth.presenter;

import android.net.Uri;

import androidx.lifecycle.SavedStateHandle;

import com.nikolam.common.navigation.DeepLinks;
import com.nikolam.common.navigation.NavManager;
import com.nikolam.common.presentation.BaseAction;
import com.nikolam.common.presentation.BaseViewModel;
import com.nikolam.common.presentation.BaseViewState;
import com.nikolam.feature_auth.data.models.LoginResponse;
import com.nikolam.feature_auth.data.models.RegistrationResponse;
import com.nikolam.feature_auth.domain.AuthSharedPreferenceManager;
import com.nikolam.feature_auth.domain.LocalRegistrationUseCase;
import com.nikolam.feature_auth.domain.TokenLoginUseCase;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import timber.log.Timber;

@HiltViewModel
public class AuthViewModel extends BaseViewModel<AuthViewModel.ViewState, AuthViewModel.Action> {

    private final NavManager navManager;
    private final LocalRegistrationUseCase localRegistration;
    private final TokenLoginUseCase tokenLogin;
    private final AuthSharedPreferenceManager prefManager;

    @Inject
    public AuthViewModel(SavedStateHandle handle, NavManager navManager, LocalRegistrationUseCase localRegistration,
                         TokenLoginUseCase tokenLogin,
                         AuthSharedPreferenceManager prefManager) {
        super(new ViewState());
        this.navManager = navManager;
        this.localRegistration = localRegistration;
        this.prefManager = prefManager;
        this.tokenLogin = tokenLogin;
    }


    public void checkSession() {
        String permalink = prefManager.getPermalink();

        if (permalink.isEmpty()) {
            Timber.d("permalink empty");
            // go to register
        } else {
            //check if token expired, if it did go to login else login
            String token = prefManager.getTokenOrNull();
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

    @NotNull
    @Override
    protected ViewState onReduceState(@NotNull AuthViewModel.Action action) {
        return null;
    }


    protected static class ViewState implements BaseViewState {
        boolean isSuccess = false;
        boolean isLoading = true;
        boolean isError = false;
        int errno = -1;

        public boolean isSuccess() {
            return isSuccess;
        }

        public boolean isLoading() {
            return isLoading;
        }

        public boolean isError() {
            return isError;
        }

        public int getErrno() {
            return errno;
        }
    }

    protected abstract class Action implements BaseAction {
        private Action() {
        }

        private final class LoginSuccess {

        }

    }

    private final class LocalRegistrationObserver extends DisposableObserver<RegistrationResponse> {
        @Override
        public void onNext(@NonNull RegistrationResponse registrationResponse) {
            prefManager.savePermalink(registrationResponse.getPermalink());
            prefManager.saveToken(registrationResponse.getToken());
            prefManager.saveUserID(registrationResponse.getId());
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
            if (loginResponse.getStatus() == 200) {
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
