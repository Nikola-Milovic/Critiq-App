package com.nikolam.feature_upload.data;

import com.nikolam.common.APIEndpoints;
import com.nikolam.feature_upload.data.models.UploadResponse;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadService {

    @Multipart
    @POST(APIEndpoints.BaseUploadAPI)
    Observable<UploadResponse> uploadCritiqImage(@Part("data") RequestBody data, @Part MultipartBody.Part image);
}
