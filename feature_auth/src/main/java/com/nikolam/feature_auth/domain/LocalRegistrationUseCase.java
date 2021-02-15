package com.nikolam.feature_auth.domain;

import com.nikolam.common.domain.UseCase;
import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.feature_auth.data.models.RegistrationModel;
import com.nikolam.feature_auth.data.models.RegistrationResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class LocalRegistrationUseCase extends UseCase<RegistrationResponse, LocalRegistrationUseCase.Params> {

    private final AuthRepository authRepository;

    @Inject
    LocalRegistrationUseCase(AuthRepository authRepository, ThreadExecutor threadExecutor,
                             PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.authRepository = authRepository;
    }

    @Override
    protected Observable<RegistrationResponse> buildUseCaseObservable(Params params) {
        assert params != null;
        return this.authRepository.localRegistration(new RegistrationModel(params.email, params.username, params.password));
    }

    public static final class Params {

        public Params(String email, String username, String password) {
            this.email = email;
            this.username = username;
            this.password = password;
        }

        private final String email;
        private final String username;
        private final String password;
    }
}
