package com.nikolam.feature_auth.data;

import com.nikolam.common.APIEndpoints;
import com.nikolam.feature_auth.data.models.LoginResponse;
import com.nikolam.feature_auth.data.models.RegistrationModel;
import com.nikolam.feature_auth.data.models.RegistrationResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthService {
    @POST(APIEndpoints.BaseAuthAPI + "/login")
    Observable<RegistrationResponse> localRegistration(@Body RegistrationModel info);

    @GET(APIEndpoints.BaseAuthAPI + "/verify")
    Observable<LoginResponse> tokenLogin(@Query("permalink") String permalink, @Query("token") String token);
}
