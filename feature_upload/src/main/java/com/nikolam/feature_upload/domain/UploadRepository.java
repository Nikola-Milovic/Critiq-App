package com.nikolam.feature_upload.domain;

import android.net.Uri;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;

public interface UploadRepository {
    Observable<Boolean> uploadCritiqImage(Uri fileUri, String id, ArrayList<String> tags, String userID);
}
