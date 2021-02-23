package com.nikolam.feature_post_detail.presenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.nikolam.common.models.CommentDomainModel;
import com.nikolam.feature_post_detail.databinding.CommentItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private ArrayList<CommentDomainModel> comments;
  //  private PostClickListener postClickListener;
    private LayoutInflater inflater;

    public CommentAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        comments = new ArrayList<>();
    }

    public void addComments(List<CommentDomainModel> data) {
        comments.clear();
        comments.addAll(data);
        notifyDataSetChanged();
    }

    // inflates the row layout from xml when needed
    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommentItemBinding binding = CommentItemBinding.inflate(inflater, parent, false);
        return new CommentViewHolder(binding);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        CommentDomainModel data = comments.get(position);
        try {
            holder.bind(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return comments.size();
    }
}
