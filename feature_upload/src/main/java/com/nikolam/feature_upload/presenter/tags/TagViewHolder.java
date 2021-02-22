package com.nikolam.feature_upload.presenter.tags;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.nikolam.feature_upload.databinding.TagOptionItemBinding;

public class TagViewHolder extends SortedListAdapter.ViewHolder<TagModel> {

    private final TagOptionItemBinding binding;
    private final TagAdapter.Listener listener;

    public TagViewHolder(TagOptionItemBinding binding, TagAdapter.Listener listener) {
        super(binding.getRoot());

        this.listener = listener;

        this.binding = binding;
    }

    @Override
    protected void performBind(@NonNull TagModel item) {
        binding.tagText.setText(item.getTag());
        binding.getRoot().setOnClickListener(v -> {
            listener.OnTagClickedListener(item);
        });
        //
    }
}