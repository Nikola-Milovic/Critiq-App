package com.nikolam.feature_main.data;


import com.nikolam.feature_main.data.models.PostDataModel;
import com.nikolam.feature_main.domain.MainRepository;
import com.nikolam.feature_main.domain.models.PostDomainModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class MainRepositoryImpl implements MainRepository {

    // private final AppRepository appRepository;
    private final MainRetrofitService retrofitService;


    @Inject
    public MainRepositoryImpl(MainRetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    @Override
    public @NonNull Observable<List<PostDomainModel>> getPosts(String id) {
        Observable<List<PostDataModel>> source = retrofitService.getPosts(id);

//        Observable<List<PostDomainModel>> observable = single.flatMapIterable(list -> list)
//                .map(PostDomainModel::dataToDomainModel).reduce();

        return source.flatMap(list ->
                Observable.fromIterable(list)
                        .map(PostDomainModel::dataToDomainModel)
                        .toList()
                        .toObservable() // Required for RxJava 2.x
        );
    }
}
