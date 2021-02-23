package com.nikolam.feature_post_detail.domain;

import com.nikolam.common.domain.UseCase;
import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.feature_post_detail.data.models.PostCommentResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class PostCommentUseCase extends UseCase<PostCommentResponse, PostCommentUseCase.Params> {

    private final PostDetailRepository postsRepository;

    @Inject
    PostCommentUseCase(PostDetailRepository postsRepository, ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.postsRepository = postsRepository;
    }

    @Override
    protected Observable<PostCommentResponse> buildUseCaseObservable(Params params) {
        assert params != null;

        Observable<PostCommentResponse> obs = this.postsRepository.postComment(params.getPostID(), params.getUserID(), params.getComment());

        return obs;
    }

    public static final class Params {
        private final String postID;
        private final String userID;
        private final String comment;

        public Params(String postID, String userID, String comment) {
            this.postID = postID;
            this.userID = userID;
            this.comment = comment;
        }

        public String getPostID() {
            return postID;
        }

        public String getUserID() {
            return userID;
        }

        public String getComment() {
            return comment;
        }
    }
}
