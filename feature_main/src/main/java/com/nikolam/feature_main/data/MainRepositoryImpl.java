package com.nikolam.feature_main.data;

import com.nikolam.common.models.PostDomainModel;
import com.nikolam.data.models.ModelMappers;
import com.nikolam.data.models.PostNetworkModel;
import com.nikolam.feature_main.domain.MainRepository;

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
        Observable<List<PostNetworkModel>> source = retrofitService.getPosts(id);

//        Observable<List<PostDomainModel>> observable = single.flatMapIterable(list -> list)
//                .map(PostDomainModel::dataToDomainModel).reduce();

        return source.flatMap(list ->
                Observable.fromIterable(list)
                        .map(ModelMappers::postNetworkToDomainModel)
                        .toList()
                        .toObservable() // Required for RxJava 2.x
        );
    }
}
