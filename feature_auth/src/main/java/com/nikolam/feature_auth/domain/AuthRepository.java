package com.nikolam.feature_auth.domain;

import com.nikolam.feature_auth.data.models.LoginResponse;
import com.nikolam.feature_auth.data.models.LoginTokenModel;
import com.nikolam.feature_auth.data.models.RegistrationModel;
import com.nikolam.feature_auth.data.models.RegistrationResponse;

import io.reactivex.rxjava3.core.Observable;

public interface AuthRepository {
    Observable<RegistrationResponse> localRegistration(RegistrationModel data);
    Observable<LoginResponse> localTokenLogin(LoginTokenModel data);
}
