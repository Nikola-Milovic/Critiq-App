package com.nikolam.feature_auth.di;

import com.nikolam.feature_auth.data.AuthRepositoryImpl;
import com.nikolam.feature_auth.data.AuthService;
import com.nikolam.feature_auth.domain.AuthRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import retrofit2.Retrofit;

@Module(includes = AuthModule.repositoryBinder.class)
@InstallIn(ViewModelComponent.class)
public class AuthModule {

    @Module
    @InstallIn(ViewModelComponent.class)
    interface repositoryBinder {
        @Binds
        AuthRepository bindServletRequest(AuthRepositoryImpl repository);
    }

    @Provides
    public AuthService provideAuthService(
            Retrofit retrofit
    ) {
        return retrofit.create(AuthService.class);
    }

};
