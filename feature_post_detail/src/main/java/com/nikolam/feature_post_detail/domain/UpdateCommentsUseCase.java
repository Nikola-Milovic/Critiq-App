package com.nikolam.feature_post_detail.domain;

import com.nikolam.common.domain.UseCase;
import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.common.models.CommentDomainModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class UpdateCommentsUseCase extends UseCase<List<CommentDomainModel>, UpdateCommentsUseCase.Params> {

    private final PostDetailRepository postsRepository;

    @Inject
    UpdateCommentsUseCase(PostDetailRepository postsRepository, ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.postsRepository = postsRepository;
    }

    @Override
    protected Observable<List<CommentDomainModel>> buildUseCaseObservable(Params params) {
        assert params != null;

        @NonNull Observable<List<CommentDomainModel>> obs = this.postsRepository.updateComments(params.getPostID());

        return obs;
    }

    public static final class Params {
        private final String postID;

        public Params(String postID) {
            this.postID = postID;
        }

        public String getPostID() {
            return postID;
        }
    }

}
