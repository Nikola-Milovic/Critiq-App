package com.nikolam.feature_post_detail.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.nikolam.feature_post_detail.databinding.PostDetailFragmentBinding;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class PostDetailFragment extends Fragment {

    private PostDetailViewModel viewModel;
    private PostDetailFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PostDetailFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PostDetailViewModel.class);

        viewModel.getPostLiveData().observe(getViewLifecycleOwner(), post -> {
            binding.commentTextView.setText(post.getComment());
            Glide.with(binding.imageImageView).load(post.getAwsImageLink()).into(binding.imageImageView);
            binding.tagsTextView.setText(post.getTags().toString());
        });

        Bundle arguments = getArguments();
        if (arguments != null) {
            String postID = arguments.getString("id");
            Timber.d("ID is %s", postID);
            viewModel.setPostID(postID);
            viewModel.getPost();
        }

    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}