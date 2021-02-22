package com.nikolam.feature_post_detail.data;

import com.nikolam.common.APIEndpoints;
import com.nikolam.feature_post_detail.data.models.PostDataModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostDetailRetrofitService {

    @GET(APIEndpoints.BasePostsAPI + "/post")
    Observable<PostDataModel> getPostByID(@Query("id") String postID);

}
