package com.nikolam.feature_main.domain;

import com.nikolam.common.models.PostDomainModel;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public interface MainRepository {
    public @NonNull Observable<List<PostDomainModel>> getPosts(String id);
}
