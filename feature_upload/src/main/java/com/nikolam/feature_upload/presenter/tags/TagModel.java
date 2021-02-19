package com.nikolam.feature_upload.presenter.tags;

import androidx.annotation.NonNull;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

import java.util.Objects;

public class TagModel implements SortedListAdapter.ViewModel {

    private final long id;
    private final String tag;

    public TagModel(long id,String tag) {
        this.id = id;
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public <T> boolean isSameModelAs(@NonNull T item) {
        if (item instanceof TagModel) {
            final TagModel tagModel = (TagModel) item;
            return tagModel.id == id;
        }
        return false;
    }

    @Override
    public <T> boolean isContentTheSameAs(@NonNull T item) {
        if (item instanceof TagModel) {
            final TagModel other = (TagModel) item;
            return tag.equals(other.tag);
        }
        return false;
    }
}