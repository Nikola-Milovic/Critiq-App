package com.nikolam.feature_main.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nikolam.common.models.PostDomainModel;
import com.nikolam.feature_main.databinding.MainFragmentBinding;
import com.nikolam.feature_main.presentation.adapter.PostAdapter;
import com.nikolam.feature_main.presentation.adapter.PostClickListener;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment implements PostClickListener {

    private MainViewModel viewModel;

    private MainFragmentBinding binding;

    private PostAdapter postAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = MainFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.uploadButton.setOnClickListener(v -> {
            viewModel.navigateToUpload();
        });

        binding.myPostsButton.setOnClickListener(v -> {
            viewModel.navigateToMyPosts();
        });


        binding.postsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        postAdapter = new PostAdapter(getContext(), this);
        binding.postsRecyclerView.setAdapter(postAdapter);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
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
    public void onPostClicked(PostDomainModel model) {
        viewModel.navigateToAPost(model.getObjectID());
    }
}