package com.nikolam.feature_main.presentation.adapter;

import com.nikolam.feature_main.domain.models.PostDomainModel;

public interface PostClickListener {
    void onPostClicked(PostDomainModel model);
}
