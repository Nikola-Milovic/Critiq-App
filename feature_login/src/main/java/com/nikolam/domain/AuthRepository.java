package com.nikolam.domain;

import com.nikolam.data.models.RegistrationModel;
import com.nikolam.data.models.RegistrationResponse;

import io.reactivex.rxjava3.core.Observable;

public interface AuthRepository {
    Observable<RegistrationResponse> localRegistration(RegistrationModel data);
}
