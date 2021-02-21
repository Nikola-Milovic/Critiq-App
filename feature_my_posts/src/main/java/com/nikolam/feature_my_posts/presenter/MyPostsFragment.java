package com.nikolam.feature_my_posts.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nikolam.data.db.models.PostDataModel;
import com.nikolam.feature_my_posts.databinding.MyPostsFragmentBinding;
import com.nikolam.feature_my_posts.domain.models.PostDomainModel;
import com.nikolam.feature_my_posts.presenter.adapter.PostAdapter;
import com.nikolam.feature_my_posts.presenter.adapter.PostClickListener;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MyPostsFragment extends Fragment implements PostClickListener {

    private MyPostsViewModel viewModel;
    private MyPostsFragmentBinding binding;
    private PostAdapter postAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MyPostsFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.postsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        postAdapter = new PostAdapter(getContext(), this);
        binding.postsRecyclerView.setAdapter(postAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MyPostsViewModel.class);
        viewModel.getPostsLiveData().observe(getViewLifecycleOwner(), postDomainModels -> {
            postAdapter.addPosts(postDomainModels);
        });
        viewModel.getPosts();
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    @Override
    public void onPostClicked(PostDataModel model) {

    }
}