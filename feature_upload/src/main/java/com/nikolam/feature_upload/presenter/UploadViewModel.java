package com.nikolam.feature_upload.presenter;

import android.net.Uri;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.nikolam.feature_upload.data.models.UploadResponse;
import com.nikolam.feature_upload.domain.UploadPostUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import timber.log.Timber;

@HiltViewModel
public class UploadViewModel extends ViewModel {

    private final UploadPostUseCase uploadPostUseCase;

    @Inject
    public UploadViewModel(SavedStateHandle handle, UploadPostUseCase uploadPostUseCase){
        this.uploadPostUseCase = uploadPostUseCase;
    }


    public void uploadPost(Uri fileUri){
        uploadPostUseCase.execute(new UploadObserver(), new UploadPostUseCase.Params(fileUri));
    }

    @Override
    protected void onCleared() {
        uploadPostUseCase.dispose();
        super.onCleared();
    }

    private static class UploadObserver extends DisposableObserver<UploadResponse>{

        @Override
        public void onNext(@NonNull UploadResponse uploadResponse) {
            Timber.d("Upload response is %s", uploadResponse.toString());
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e(e);
        }

        @Override
        public void onComplete() {
            Timber.d("Upload completed");
        }
    }
}