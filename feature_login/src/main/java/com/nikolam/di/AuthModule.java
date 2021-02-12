package com.nikolam.di;

import com.nikolam.data.AuthRepositoryImpl;
import com.nikolam.data.AuthService;
import com.nikolam.domain.AuthRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;
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
