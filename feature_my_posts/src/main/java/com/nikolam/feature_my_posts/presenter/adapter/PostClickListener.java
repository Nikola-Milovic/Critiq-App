package com.nikolam.feature_my_posts.presenter.adapter;

import com.nikolam.common.models.PostDomainModel;

public interface PostClickListener {
    void onPostClicked(PostDomainModel model);
}
