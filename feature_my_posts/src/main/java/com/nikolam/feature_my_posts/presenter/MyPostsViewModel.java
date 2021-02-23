package com.nikolam.feature_my_posts.presenter;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.nikolam.common.models.PostDomainModel;
import com.nikolam.common.navigation.DeepLinks;
import com.nikolam.common.navigation.NavManager;
import com.nikolam.feature_my_posts.domain.GetPostsUseCase;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import timber.log.Timber;


@HiltViewModel
public class MyPostsViewModel extends ViewModel {

    private final GetPostsUseCase getPostsUseCase;
    private final NavManager navManager;

    private MutableLiveData<List<PostDomainModel>> postsLiveData = new MutableLiveData<List<PostDomainModel>>() {
    };

    @Inject
    MyPostsViewModel(SavedStateHandle state, GetPostsUseCase getPostsUseCase, NavManager navManager) {
        this.getPostsUseCase = getPostsUseCase;
        this.navManager = navManager;
    }

    public LiveData<List<PostDomainModel>> getPostsLiveData() {
        if (postsLiveData == null) {
            postsLiveData = new MutableLiveData<>();
        }
        return postsLiveData;
    }

    void getPosts() {
        getPostsUseCase.execute(new GetPostsObserver(), null);
    }

    @Override
    protected void onCleared() {
        getPostsUseCase.dispose();
        super.onCleared();
    }

    void navigateToAPost(String postID) {
        Uri uri = Uri.parse(DeepLinks.POST_DETAIL_DEEPLINK + "/" + postID);
        navManager.navigate(uri);
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