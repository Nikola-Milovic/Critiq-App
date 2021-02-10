package com.nikolam.di;

import com.nikolam.feature_login.LoginViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val module = module {
    viewModel { LoginViewModel() }
}