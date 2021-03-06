package com.nikolam.feature_post_detail.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.nikolam.common.models.CommentDomainModel;
import com.nikolam.common.navigation.NavManager;
import com.nikolam.feature_post_detail.data.models.PostCommentResponse;
import com.nikolam.feature_post_detail.domain.GetPostUseCase;
import com.nikolam.feature_post_detail.domain.PostCommentUseCase;
import com.nikolam.common.models.PostDomainModel;
import com.nikolam.feature_post_detail.domain.UpdateCommentsUseCase;

import java.util.ArrayList;
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
    private final PostCommentUseCase postCommentUseCase;
    private final UpdateCommentsUseCase updateCommentsUseCase;
    private String postID;

    private final NavManager navManager;

    private MutableLiveData<PostDomainModel> postLiveData = new MutableLiveData<PostDomainModel>() {
    };

    private MutableLiveData<Boolean> isMinePostLiveData = new MutableLiveData<Boolean>() {
    };

    @Inject
    public PostDetailViewModel(SavedStateHandle state, @Named("userID") String userID, GetPostUseCase getPostUseCase, PostCommentUseCase postCommentUseCase,
                               UpdateCommentsUseCase updateCommentsUseCase, NavManager navManager) {
        this.userID = userID;
        this.getPostUseCase = getPostUseCase;
        this.updateCommentsUseCase = updateCommentsUseCase;
        this.navManager = navManager;
        this.postCommentUseCase = postCommentUseCase;
    }

    public LiveData<PostDomainModel> getPostLiveData() {
        if (postLiveData == null) {
            postLiveData = new MutableLiveData<>();
        }
        return postLiveData;
    }

    public LiveData<Boolean> getIsMinePostLiveData() {
        if (isMinePostLiveData == null) {
            isMinePostLiveData = new MutableLiveData<>();
        }
        return isMinePostLiveData;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public void getPost() {
        getPostUseCase.execute(new GetPostObserver(), new GetPostUseCase.Params(postID));
    }

    public void goBack() {
        navManager.popBackStack();
    }

    public void postComment(String contents) {
        postCommentUseCase.execute(new PostCommentObserver(), new PostCommentUseCase.Params(postID, userID,contents));
    }

    @Override
    protected void onCleared() {
        updateCommentsUseCase.dispose();
        postCommentUseCase.dispose();
        getPostUseCase.dispose();
        super.onCleared();
    }

    private class GetPostObserver extends DisposableObserver<PostDomainModel> {

        @Override
        public void onNext(@NonNull PostDomainModel post) {
            // Timber.d("Post is %s", post.toString());
            postLiveData.setValue(post);
            boolean isMine = post.getUserID().equals(userID);
            isMinePostLiveData.setValue(isMine);
            if (isMine) {
                updateCommentsUseCase.execute(new UpdateCommentsObserver(), new UpdateCommentsUseCase.Params(postID));
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e(e);
        }

        @Override
        public void onComplete() {
            //Timber.d("Post fetching completed");
        }
    }

    private class PostCommentObserver extends DisposableObserver<PostCommentResponse> {

        @Override
        public void onNext(@NonNull PostCommentResponse comment) {
            Timber.d("Post response status is %s", comment.getStatus());
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e(e);
        }

        @Override
        public void onComplete() {
        }
    }

    private class UpdateCommentsObserver extends DisposableObserver<List<CommentDomainModel>> {

        @Override
        public void onNext(@NonNull List<CommentDomainModel> comments) {
            PostDomainModel current = getPostLiveData().getValue();
            current.setComments((ArrayList<CommentDomainModel>) comments);
            postLiveData.setValue(current);
            Timber.d("Updated comments %s", comments.toString());
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Timber.e(e);
        }

        @Override
        public void onComplete() {
            //Timber.d("Post fetching completed");
        }
    }
}