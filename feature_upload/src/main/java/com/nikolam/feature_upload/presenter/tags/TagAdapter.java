package com.nikolam.feature_upload.presenter.tags;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.nikolam.feature_upload.databinding.TagOptionItemBinding;

import java.util.Comparator;

public class TagAdapter extends SortedListAdapter<TagModel> {

    public interface Listener {
        void OnTagClickedListener(TagModel model);
    }

    private final Listener mListener;

    public TagAdapter(Context context, Comparator<TagModel> comparator, Listener listener) {
        super(context, TagModel.class, comparator);
        mListener = listener;
    }

    @NonNull
    @Override
    protected ViewHolder<? extends TagModel> onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int viewType) {
        final TagOptionItemBinding binding = TagOptionItemBinding.inflate(inflater, parent, false);
        return new TagViewHolder(binding, mListener);
    }
}