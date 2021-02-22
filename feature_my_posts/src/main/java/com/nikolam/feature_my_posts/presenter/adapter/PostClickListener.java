package com.nikolam.feature_my_posts.presenter.adapter;

import com.nikolam.feature_my_posts.domain.models.PostDomainModel;

public interface PostClickListener {
    void onPostClicked(PostDomainModel model);
}
