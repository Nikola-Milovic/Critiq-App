package com.nikolam.feature_post_detail.domain;

import com.nikolam.common.domain.UseCase;
import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.common.models.PostDomainModel;


import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetPostUseCase extends UseCase<PostDomainModel, GetPostUseCase.Params> {

    private final PostDetailRepository postsRepository;

    @Inject
    GetPostUseCase(PostDetailRepository postsRepository, ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.postsRepository = postsRepository;
    }

    @Override
    protected Observable<PostDomainModel> buildUseCaseObservable(Params params) {
        assert params != null;

        Observable<PostDomainModel> obs = this.postsRepository.getPost(params.getPostID());

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
