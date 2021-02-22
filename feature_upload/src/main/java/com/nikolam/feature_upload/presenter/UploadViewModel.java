package com.nikolam.feature_upload.presenter;

import android.net.Uri;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.nikolam.feature_upload.domain.UploadPostUseCase;
import com.nikolam.feature_upload.presenter.tags.TagModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import timber.log.Timber;

@HiltViewModel
public class UploadViewModel extends ViewModel {

    private final UploadPostUseCase uploadPostUseCase;
    private final String userID;

    @Inject
    public UploadViewModel(SavedStateHandle handle, UploadPostUseCase uploadPostUseCase, @Named("userID") String id) {
        this.uploadPostUseCase = uploadPostUseCase;
        this.userID = id;
    }


    public void uploadPost(Uri fileUri, List<TagModel> selectedTags, String comment) {
        ArrayList<String> tags = new ArrayList<>();
        for (TagModel m : selectedTags) {
            tags.add(m.getTag().toLowerCase());
        }

        uploadPostUseCase.execute(new UploadObserver(), new UploadPostUseCase.Params(fileUri, userID, tags, comment));
    }

    @Override
    protected void onCleared() {
        uploadPostUseCase.dispose();
        super.onCleared();
    }

    private static class UploadObserver extends DisposableObserver<Boolean> {

        @Override
        public void onNext(@NonNull Boolean bool) {
            Timber.d("Upload response is %s", bool);
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