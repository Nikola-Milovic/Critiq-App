package com.nikolam.data.db;

import com.nikolam.common.APIEndpoints;

import io.reactivex.rxjava3.core.Completable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DataRetrofitService {

    @POST(APIEndpoints.BaseAccountAPI + "tok")
    Completable saveToken(@Query("id") String userID, @Body AppRepository.FCMTokenMessage token);
}
