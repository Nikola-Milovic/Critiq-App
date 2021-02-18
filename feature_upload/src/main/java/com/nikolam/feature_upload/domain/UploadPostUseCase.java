package com.nikolam.feature_upload.domain;

import android.net.Uri;

import com.nikolam.common.domain.UseCase;
import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.feature_upload.data.models.UploadResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class UploadPostUseCase extends UseCase<UploadResponse, UploadPostUseCase.Params> {

    private final UploadRepository uploadRepository;

    @Inject
    UploadPostUseCase(UploadRepository uploadRepository, ThreadExecutor threadExecutor,
                      PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.uploadRepository = uploadRepository;
    }

    @Override
    protected Observable<UploadResponse> buildUseCaseObservable(Params params) {
        assert params != null;
        return this.uploadRepository.uploadCritiqImage(params.fileUri);
    }

    public static final class Params {

        private final Uri fileUri;

        public Params(Uri fileUri) {
            this.fileUri = fileUri;
        }
    }
}
