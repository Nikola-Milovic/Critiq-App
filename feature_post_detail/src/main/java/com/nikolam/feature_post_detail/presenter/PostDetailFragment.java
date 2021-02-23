package com.nikolam.feature_post_detail.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.nikolam.feature_post_detail.R;
import com.nikolam.feature_post_detail.databinding.PostDetailFragmentBinding;
import com.nikolam.feature_post_detail.presenter.adapter.CommentAdapter;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class PostDetailFragment extends Fragment {

    private PostDetailViewModel viewModel;
    private PostDetailFragmentBinding binding;
    private CommentAdapter commentAdapter;

    private boolean isMine;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PostDetailFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.menu.setOnClickListener(v -> {
            if (isMine){
                showMyPostMenu(binding.menu);
            } else {
                showPostMenu(binding.menu);
            }
        });

        binding.goBackButton.setOnClickListener(v -> {
            viewModel.goBack();
        });

        binding.postCommentButton.setOnClickListener(v -> {
            viewModel.postComment(binding.commentTextView.getText().toString());
        });

        binding.commentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        commentAdapter = new CommentAdapter(getContext());
        binding.commentsRecyclerView.setAdapter(commentAdapter);

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
            commentAdapter.addComments(post.getComments());
        });

        viewModel.getIsMinePostLiveData().observe(getViewLifecycleOwner(), isMinePost -> {
            isMine = isMinePost;
            if (isMine){
                binding.postComment.setVisibility(View.INVISIBLE);
            }
        });

        Bundle arguments = getArguments();
        if (arguments != null) {
            String postID = arguments.getString("id");
            Timber.d("ID is %s", postID);
            viewModel.setPostID(postID);
            viewModel.getPost();
        }

    }

    private void showPostMenu(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.getMenuInflater().inflate(R.menu.post_menu, popup.getMenu());

        popup.setOnMenuItemClickListener( menuItem -> {
                    if (menuItem.getItemId() == R.id.save_post_menu_item) {
                        Timber.d("Save post");
                    } else if(menuItem.getItemId() == R.id.share_post_menu_item){
                        Timber.d("Share post");
                    }
                    return false;
                }
            );
        // Show the popup menu.
        popup.show();
    }

    private void showMyPostMenu(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.getMenuInflater().inflate(R.menu.my_post_menu, popup.getMenu());

        popup.setOnMenuItemClickListener( menuItem -> {
                    if (menuItem.getItemId() == R.id.delete_mpost_menu_item) {
                        Timber.d("Delete post");
                    } else if(menuItem.getItemId() == R.id.share_mpost_menu_item){
                        Timber.d("Share post");
                    }
                    return false;
                }
        );
        // Show the popup menu.
        popup.show();
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}