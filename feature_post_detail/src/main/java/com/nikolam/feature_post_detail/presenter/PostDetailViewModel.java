package com.nikolam.feature_post_detail.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.nikolam.common.navigation.NavManager;
import com.nikolam.feature_post_detail.domain.GetPostUseCase;
import com.nikolam.feature_post_detail.domain.models.PostDomainModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import timber.log.Timber;

@HiltViewModel
public class PostDetailViewModel extends ViewModel {

    private final String userID;
    private final GetPostUseCase getPostUseCase;
    private String postID;

    private MutableLiveData<PostDomainModel> postLiveData = new MutableLiveData<PostDomainModel>() {
    };


    @Inject
    public PostDetailViewModel(SavedStateHandle state, @Named("userID") String userID, GetPostUseCase getPostUseCase) {
        this.userID = userID;
        this.getPostUseCase = getPostUseCase;
    }

    public LiveData<PostDomainModel> getPostLiveData() {
        if (postLiveData == null) {
            postLiveData = new MutableLiveData<>();
        }
        return postLiveData;
    }



    public void setPostID(String postID) {
        this.postID = postID;
    }

    public void getPost() {
        getPostUseCase.execute(new GetPostObserver(), new GetPostUseCase.Params(postID));
    }

    @Override
    protected void onCleared() {
        getPostUseCase.dispose();
        super.onCleared();
    }

    private class GetPostObserver extends DisposableObserver<PostDomainModel> {

        @Override
        public void onNext(@NonNull PostDomainModel post) {
            Timber.d("Post is %s", post.toString());
            postLiveData.setValue(post);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e(e);
        }

        @Override
        public void onComplete() {
            Timber.d("Post fetching completed");
        }
    }
}