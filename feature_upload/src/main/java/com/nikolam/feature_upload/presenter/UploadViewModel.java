package com.nikolam.feature_upload.presenter;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UploadViewModel extends ViewModel {
    @Inject
    public UploadViewModel(SavedStateHandle handle){}

    String currentPhotoPath;


}