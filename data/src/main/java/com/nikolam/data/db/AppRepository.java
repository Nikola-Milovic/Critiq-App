package com.nikolam.data.db;

import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.data.db.models.PostDataModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class AppRepository {

    private final AppDatabase db;
    private final ThreadExecutor threadExecutor;

    @Inject
    public AppRepository(AppDatabase db, ThreadExecutor threadExecutor,
                         PostExecutionThread postExecutionThread) {
        this.db = db;
        this.threadExecutor = threadExecutor;
    }

    public void insertPost(PostDataModel post){
        db.postDao().insertPost(post).subscribeOn(
                Schedulers.from(threadExecutor)
        ).subscribe();
    }

    public Observable<List<PostDataModel>> getPosts(){
        return db.postDao().getMyPosts();
    }
}