package com.nikolam.feature_my_posts.presenter.adapter;

import com.nikolam.data.db.models.PostDataModel;

public interface PostClickListener {
    void onPostClicked(PostDataModel model);
}
