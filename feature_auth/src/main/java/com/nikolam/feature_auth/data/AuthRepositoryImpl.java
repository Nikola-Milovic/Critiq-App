package com.nikolam.feature_auth.data;

import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.feature_auth.data.models.LoginResponse;
import com.nikolam.feature_auth.data.models.LoginTokenModel;
import com.nikolam.feature_auth.data.models.RegistrationModel;
import com.nikolam.feature_auth.data.models.RegistrationResponse;
import com.nikolam.feature_auth.domain.AuthSharedPreferenceManager;
import com.nikolam.feature_auth.domain.AuthRepository;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ViewModelScoped;
import io.reactivex.rxjava3.core.Observable;
import timber.log.Timber;

@ViewModelScoped
public class AuthRepositoryImpl implements AuthRepository {
    private final AuthService service;
    private final AuthSharedPreferenceManager loginVerifier;
    private final ThreadExecutor threadExecutor;

    @Inject
    public AuthRepositoryImpl(AuthService service, AuthSharedPreferenceManager verifier, ThreadExecutor threadExecutor) {
        this.service = service;
        this.loginVerifier = verifier;
        this.threadExecutor = threadExecutor;
    }

    @Override
    public Observable<RegistrationResponse> localRegistration(RegistrationModel data) {
        Timber.d("Call");

        Observable<RegistrationResponse> observable = service.localRegistration(data);

        return observable;
    }

    @Override
    public Observable<LoginResponse> localTokenLogin(LoginTokenModel data) {

        Observable<LoginResponse> observable = service.tokenLogin(data.getPermalink(), data.getToken());

        return observable;
    }
}
