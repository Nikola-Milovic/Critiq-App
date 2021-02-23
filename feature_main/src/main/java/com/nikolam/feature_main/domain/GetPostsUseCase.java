package com.nikolam.feature_main.domain;

import com.nikolam.common.domain.UseCase;
import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.common.models.PostDomainModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class GetPostsUseCase extends UseCase<List<PostDomainModel>, GetPostsUseCase.Params> {

    private final MainRepository mainRepository;

    @Inject
    GetPostsUseCase(MainRepository postsRepository, ThreadExecutor threadExecutor,
                    PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mainRepository = postsRepository;
    }

    @Override
    protected Observable<List<PostDomainModel>> buildUseCaseObservable(Params params) {
        assert params != null;

        Observable<List<PostDomainModel>> obs = this.mainRepository.getPosts(params.getUserID());

        return obs;
    }

    public static final class Params {
        private final String userID;

        public Params(String userID) {
            this.userID = userID;
        }

        public String getUserID() {
            return userID;
        }
    }
}
