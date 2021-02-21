package com.nikolam.feature_my_posts.domain;

import com.nikolam.feature_my_posts.domain.models.PostDomainModel;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public interface MyPostsRepository {
    public @NonNull Observable<List<PostDomainModel>> getPosts();
}
