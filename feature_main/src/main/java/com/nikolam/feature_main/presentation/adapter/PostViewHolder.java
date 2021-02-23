package com.nikolam.feature_main.presentation.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nikolam.common.models.PostDomainModel;
import com.nikolam.feature_main.databinding.PostItemBinding;

public class PostViewHolder extends RecyclerView.ViewHolder {

    private PostItemBinding binding;
    private PostClickListener listener;
    private PostDomainModel model;

    private PostViewHolder(View itemView) {
        super(itemView);
    }

    PostViewHolder(PostItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(PostClickListener l, PostDomainModel m) {
        this.listener = l;
        this.model = m;

        binding.commentTextView.setText(m.getComment());

        StringBuilder sb = new StringBuilder();

        for (String s : m.getTags()) {
            sb.append(s).append(" ");
        }

        binding.tagsTextView.setText(sb.toString());

        binding.getRoot().setOnClickListener(v -> {
            listener.onPostClicked(model);
        });

        Glide.with(binding.getRoot().getContext()).load(model.getThumbnailLink()).into(binding.thumbnailImageView);

    }
}
