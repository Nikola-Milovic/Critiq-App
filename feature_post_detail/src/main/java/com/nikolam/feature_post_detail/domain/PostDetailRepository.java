package com.nikolam.feature_post_detail.domain;


import com.nikolam.common.models.CommentDomainModel;
import com.nikolam.feature_post_detail.data.models.PostCommentResponse;
import com.nikolam.common.models.PostDomainModel;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public interface PostDetailRepository {
    public @NonNull Observable<PostDomainModel> getPost(String id);
    public @NonNull Observable<PostCommentResponse> postComment(String postID, String userID, String comment);
    public @NonNull Observable<List<CommentDomainModel>> updateComments(String postID);
}
