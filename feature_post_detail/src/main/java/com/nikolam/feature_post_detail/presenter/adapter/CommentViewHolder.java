package com.nikolam.feature_post_detail.presenter.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nikolam.common.models.CommentDomainModel;
import com.nikolam.common.models.PostDomainModel;
import com.nikolam.feature_post_detail.databinding.CommentItemBinding;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private CommentItemBinding binding;
    private CommentDomainModel model;

    private CommentViewHolder(View itemView) {
        super(itemView);
    }

    CommentViewHolder(CommentItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(CommentDomainModel m) {
        this.model = m;

        binding.commentItemTextView.setText(m.getContents());
    }
}
