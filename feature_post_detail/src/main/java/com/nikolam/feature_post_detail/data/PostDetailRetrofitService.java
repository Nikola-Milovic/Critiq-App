package com.nikolam.feature_post_detail.data;

import com.nikolam.common.APIEndpoints;
import com.nikolam.feature_post_detail.data.models.PostCommentResponse;
import com.nikolam.data.models.PostNetworkModel;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PostDetailRetrofitService {

    @GET(APIEndpoints.BasePostsAPI + "/post")
    Single<PostNetworkModel> getPostByID(@Query("id") String postID);

    @POST(APIEndpoints.BasePostsAPI + "/post/comments")
    Single<PostCommentResponse> postComment(@Body PostDetailRepositoryImpl.Comment comment);
}
