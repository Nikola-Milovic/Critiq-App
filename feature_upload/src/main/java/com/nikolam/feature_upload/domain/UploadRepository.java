package com.nikolam.feature_upload.domain;

import android.net.Uri;

import com.nikolam.feature_upload.data.models.UploadResponse;

import io.reactivex.rxjava3.core.Observable;

public interface UploadRepository {
    Observable<UploadResponse> uploadCritiqImage(Uri fileUri);
}
