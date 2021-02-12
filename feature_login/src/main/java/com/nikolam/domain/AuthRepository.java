package com.nikolam.domain;

import com.nikolam.data.models.RegistrationModel;
import com.nikolam.data.models.RegistrationResponse;

public interface AuthRepository {
    void localRegistration(RegistrationModel data);
}
