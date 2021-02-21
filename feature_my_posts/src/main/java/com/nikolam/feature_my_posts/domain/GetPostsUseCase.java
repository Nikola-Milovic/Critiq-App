package com.nikolam.feature_my_posts.domain;

import com.nikolam.common.domain.UseCase;
import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.feature_my_posts.domain.models.PostDomainModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetPostsUseCase extends UseCase<List<PostDomainModel>, GetPostsUseCase.Params> {

    private final MyPostsRepository postsRepository;

    @Inject
    GetPostsUseCase(MyPostsRepository postsRepository, ThreadExecutor threadExecutor,
                    PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.postsRepository = postsRepository;
    }

    @Override
    protected Observable<List<PostDomainModel>> buildUseCaseObservable(Params params) {
        assert params == null;

        Observable<List<PostDomainModel>> obs = this.postsRepository.getPosts();

        return obs;
    }

    public static final class Params {
        private Params() {
        }
    }
}
