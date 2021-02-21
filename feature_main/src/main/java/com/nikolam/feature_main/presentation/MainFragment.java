package com.nikolam.feature_main.presentation;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nikolam.feature_main.R;
import com.nikolam.feature_main.databinding.MainFragmentBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment {

    private MainViewModel viewModel;

    private MainFragmentBinding binding;
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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }


    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}