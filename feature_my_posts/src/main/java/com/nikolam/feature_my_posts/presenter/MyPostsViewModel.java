package com.nikolam.feature_my_posts.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.nikolam.feature_my_posts.domain.GetPostsUseCase;
import com.nikolam.feature_my_posts.domain.models.PostDomainModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import timber.log.Timber;


@HiltViewModel
public class MyPostsViewModel extends ViewModel {

    private final GetPostsUseCase getPostsUseCase;

    private MutableLiveData<List<PostDomainModel>> postsLiveData = new MutableLiveData<List<PostDomainModel>>() {
    };

    @Inject
    MyPostsViewModel(SavedStateHandle state, GetPostsUseCase getPostsUseCase) {
        this.getPostsUseCase = getPostsUseCase;
    }

    public LiveData<List<PostDomainModel>> getPostsLiveData() {
        if (postsLiveData == null) {
            postsLiveData = new MutableLiveData<>();
        }
        return postsLiveData;
    }

    void getPosts() {
        Timber.d("GetPosts");
        getPostsUseCase.execute(new GetPostsObserver(), null);
    }

    @Override
    protected void onCleared() {
        getPostsUseCase.dispose();
        super.onCleared();
    }

    private final class GetPostsObserver extends DisposableObserver<List<PostDomainModel>> {
        @Override
        public void onNext(@NonNull List<PostDomainModel> posts) {
            postsLiveData.setValue(posts);
            Timber.d(posts.toString());
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e(e);
        }

        @Override
        public void onComplete() {
            Timber.d("Complete");
        }
    }
}