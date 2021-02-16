package com.nikolam.feature_auth.presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.nikolam.feature_auth.databinding.AuthFragmentBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthFragment extends Fragment {
    private AuthViewModel viewModel;

    private AuthFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = AuthFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.registerButton.setOnClickListener(v -> viewModel.localRegistration(
                binding.usernameEditTextRegister.getText().toString(),
                binding.emailEditTextRegister.getText().toString(),
                binding.passwordEditTextRegister.getText().toString()
        ));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        viewModel.checkSession();
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }
}