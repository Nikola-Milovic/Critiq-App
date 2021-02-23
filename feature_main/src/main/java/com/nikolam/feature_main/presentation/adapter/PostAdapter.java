package com.nikolam.feature_main.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.nikolam.common.models.PostDomainModel;
import com.nikolam.feature_main.databinding.PostItemBinding;

import java.util.ArrayList;
import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private ArrayList<PostDomainModel> posts;
    private PostClickListener postClickListener;
    private LayoutInflater inflater;

    public PostAdapter(Context context, PostClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        posts = new ArrayList<>();
        this.postClickListener = listener;
    }

    public void addPosts(List<PostDomainModel> data) {
        posts.clear();
        posts.addAll(data);
        notifyDataSetChanged();
    }

    // inflates the row layout from xml when needed
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PostItemBinding binding = PostItemBinding.inflate(inflater, parent, false);
        return new PostViewHolder(binding);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        PostDomainModel data = posts.get(position);
        try {
            holder.bind(postClickListener, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return posts.size();
    }
}
