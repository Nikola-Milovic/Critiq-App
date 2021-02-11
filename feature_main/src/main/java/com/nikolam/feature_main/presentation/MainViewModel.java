package com.nikolam.feature_main.presentation;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class MainViewModel extends ViewModel {
    @Inject
    public MainViewModel(SavedStateHandle handle){}
}