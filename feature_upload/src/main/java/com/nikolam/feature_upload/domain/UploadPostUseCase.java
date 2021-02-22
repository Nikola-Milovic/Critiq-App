package com.nikolam.feature_upload.domain;

import android.net.Uri;

import com.nikolam.common.domain.UseCase;
import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class UploadPostUseCase extends UseCase<Boolean, UploadPostUseCase.Params> {

    private final UploadRepository uploadRepository;

    @Inject
    UploadPostUseCase(UploadRepository uploadRepository, ThreadExecutor threadExecutor,
                      PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.uploadRepository = uploadRepository;
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable(Params params) {
        assert params != null;
        return this.uploadRepository.uploadCritiqImage(params.fileUri, params.userID, params.tags, params.comment);
    }

    public static final class Params {

        private final Uri fileUri;
        private final String userID;
        private final String comment;
        private final ArrayList<String> tags;

        public Params(Uri fileUri, String userID, ArrayList<String> tags, String comment) {
            this.fileUri = fileUri;
            this.userID = userID;
            this.comment = comment;
            this.tags = tags;
        }
    }
}
