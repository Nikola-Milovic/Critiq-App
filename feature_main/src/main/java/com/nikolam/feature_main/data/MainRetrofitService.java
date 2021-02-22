package com.nikolam.feature_main.data;

import com.nikolam.common.APIEndpoints;
import com.nikolam.feature_main.data.models.PostDataModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainRetrofitService {

    @GET(APIEndpoints.BasePostsAPI + "/")
    Observable<List<PostDataModel>> getPosts(@Query("id") String userID);

}
