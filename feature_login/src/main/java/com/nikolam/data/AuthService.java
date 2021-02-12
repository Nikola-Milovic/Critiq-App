package com.nikolam.data;

import com.nikolam.common.APIEndpoints;
import com.nikolam.data.models.RegistrationModel;
import com.nikolam.data.models.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST(APIEndpoints.BaseAuthAPI + "/login")
    Call<RegistrationResponse> localRegistration(@Body RegistrationModel info);
}
