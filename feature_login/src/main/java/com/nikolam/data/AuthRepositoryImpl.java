package com.nikolam.data;

import com.nikolam.data.models.RegistrationModel;
import com.nikolam.data.models.RegistrationResponse;
import com.nikolam.domain.AuthLoginVerifier;
import com.nikolam.domain.AuthRepository;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ViewModelScoped;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import timber.log.Timber;

@ViewModelScoped
public class AuthRepositoryImpl implements AuthRepository {
    private final AuthService service;
    private final AuthLoginVerifier loginVerifier;

    @Inject
    public AuthRepositoryImpl(AuthService service, AuthLoginVerifier verifier) {
        this.service = service;
        this.loginVerifier = verifier;
    }

    @Override
    public Observable<RegistrationResponse> localRegistration(RegistrationModel data) {
        Timber.d("Call");

        Observable<RegistrationResponse> observable = service.localRegistration(data);

        observable.subscribe(new LocalRegistrationObserver());


        return observable;
    }


    private final class LocalRegistrationObserver extends DisposableObserver<RegistrationResponse> {
        @Override
        public void onNext(@NonNull RegistrationResponse registrationResponse) {
            loginVerifier.savePermalink(registrationResponse.getPermalink());
            loginVerifier.saveToken(registrationResponse.getToken());
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e(e);
        }

        @Override
        public void onComplete() {
            Timber.d("Complete repo");
        }
    }
}
