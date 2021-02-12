package com.nikolam.data;

import com.nikolam.data.models.RegistrationModel;
import com.nikolam.data.models.RegistrationResponse;
import com.nikolam.domain.AuthRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ViewModelScoped;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

@ViewModelScoped
public class AuthRepositoryImpl implements AuthRepository {
    private final AuthService service;

    @Inject
    public AuthRepositoryImpl(AuthService service) {
        this.service = service;
    }

    @Override
    public void localRegistration(RegistrationModel data) {
        Timber.d("Call");
        Call<RegistrationResponse> call = service.localRegistration(data);
        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                Timber.d(response.toString());
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                Timber.e(t);
            }
        });
    }

}
