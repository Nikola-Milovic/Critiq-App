package com.nikolam.feature_auth.domain;

import com.nikolam.common.domain.UseCase;
import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.feature_auth.data.models.LoginResponse;
import com.nikolam.feature_auth.data.models.LoginTokenModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class TokenLoginUseCase extends UseCase<LoginResponse, TokenLoginUseCase.Params> {

    private final AuthRepository authRepository;

    @Inject
    TokenLoginUseCase(AuthRepository authRepository, ThreadExecutor threadExecutor,
                      PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.authRepository = authRepository;
    }

    @Override
    protected Observable<LoginResponse> buildUseCaseObservable(Params params) {
        assert params != null;
        return this.authRepository.localTokenLogin(new LoginTokenModel(params.token, params.permalink));
    }

    public static final class Params {

        private final String token;
        private final String permalink;

        public Params(String token, String permalink) {
            this.token = token;
            this.permalink = permalink;
        }
    }
}
