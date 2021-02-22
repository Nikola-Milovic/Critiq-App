package com.nikolam.feature_post_detail.data;

import com.nikolam.feature_post_detail.data.models.PostDataModel;
import com.nikolam.feature_post_detail.domain.PostDetailRepository;
import com.nikolam.feature_post_detail.domain.models.PostDomainModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class PostDetailRepositoryImpl implements PostDetailRepository {

    // private final AppRepository appRepository;
    private final PostDetailRetrofitService retrofitService;


    @Inject
    public PostDetailRepositoryImpl(PostDetailRetrofitService retrofitService) {
        this.retrofitService = retrofitService;
    }

    @Override
    public @NonNull Observable<PostDomainModel> getPost(String postID) {

        Observable<PostDataModel> source = retrofitService.getPostByID(postID);

//        Observable<List<PostDomainModel>> observable = single.flatMapIterable(list -> list)
//                .map(PostDomainModel::dataToDomainModel).reduce();

        return source.map(PostDomainModel::dataToDomainModel);
    }
}
