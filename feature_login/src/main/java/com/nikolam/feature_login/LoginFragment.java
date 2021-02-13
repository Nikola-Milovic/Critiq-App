package com.nikolam.feature_login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nikolam.feature_login.databinding.LoginFragmentBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {
    private LoginViewModel viewModel;

    private LoginFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = LoginFragmentBinding.inflate(inflater, container, false);
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
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

}