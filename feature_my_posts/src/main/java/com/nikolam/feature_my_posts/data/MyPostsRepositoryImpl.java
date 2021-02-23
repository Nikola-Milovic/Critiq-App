package com.nikolam.feature_my_posts.data;

import com.nikolam.common.models.PostDomainModel;
import com.nikolam.data.db.AppRepository;
import com.nikolam.data.db.models.PostDataModel;
import com.nikolam.data.models.ModelMappers;
import com.nikolam.feature_my_posts.domain.MyPostsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class MyPostsRepositoryImpl implements MyPostsRepository {

    private final AppRepository appRepository;

    @Inject
    public MyPostsRepositoryImpl(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    public @NonNull Observable<List<PostDomainModel>> getPosts() {
        Observable<List<PostDataModel>> source = appRepository.getPosts();

//        Observable<List<PostDomainModel>> observable = single.flatMapIterable(list -> list)
//                .map(PostDomainModel::dataToDomainModel).reduce();

        return source.flatMap(list ->
                Observable.fromIterable(list)
                        .map(ModelMappers::postDataToDomainModel)
                        .toList()
                        .toObservable() // Required for RxJava 2.x
        );
    }
}
