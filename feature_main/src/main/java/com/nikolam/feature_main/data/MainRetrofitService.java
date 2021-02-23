package com.nikolam.feature_main.data;

import com.nikolam.common.APIEndpoints;
import com.nikolam.data.models.PostNetworkModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainRetrofitService {

    @GET(APIEndpoints.BasePostsAPI + "/")
    Observable<List<PostNetworkModel>> getPosts(@Query("id") String userID);

}
