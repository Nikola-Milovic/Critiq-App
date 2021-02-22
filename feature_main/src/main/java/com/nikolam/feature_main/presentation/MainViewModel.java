package com.nikolam.feature_main.presentation;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.nikolam.common.navigation.DeepLinks;
import com.nikolam.common.navigation.NavManager;
import com.nikolam.feature_main.domain.GetPostsUseCase;
import com.nikolam.feature_main.domain.models.PostDomainModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import timber.log.Timber;


@HiltViewModel
public class MainViewModel extends ViewModel {

    private final NavManager navManager;
    private final GetPostsUseCase getPostsUseCase;
    private final String userID;

    private MutableLiveData<List<PostDomainModel>> postsLiveData = new MutableLiveData<List<PostDomainModel>>() {
    };

    @Inject
    public MainViewModel(SavedStateHandle handle, NavManager navManager, GetPostsUseCase getPostsUseCase,  @Named("userID") String id) {
        this.navManager = navManager;
        this.getPostsUseCase = getPostsUseCase;
        this.userID = id;
    }

    public LiveData<List<PostDomainModel>> getPostsLiveData() {
        if (postsLiveData == null) {
            postsLiveData = new MutableLiveData<>();
        }
        return postsLiveData;
    }

    void getPosts(){
        getPostsUseCase.execute(new GetPostsObserver(), new GetPostsUseCase.Params(userID));
    }

    void navigateToUpload() {
        Uri uri = Uri.parse(DeepLinks.UPLOAD_DEEPLINK);
        navManager.navigate(uri);
    }

    void navigateToMyPosts() {
        Uri uri = Uri.parse(DeepLinks.MY_POSTS_DEEPLINK);
        navManager.navigate(uri);
    }

    @Override
    protected void onCleared() {
        getPostsUseCase.dispose();
        super.onCleared();
    }

    private class GetPostsObserver extends DisposableObserver<List<PostDomainModel>> {

        @Override
        public void onNext(@NonNull List<PostDomainModel> posts) {
            Timber.d("Posts are %s", posts.toString());
            postsLiveData.setValue(posts);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e(e);
        }

        @Override
        public void onComplete() {
            Timber.d("Posts fetching completed");
        }
    }
}