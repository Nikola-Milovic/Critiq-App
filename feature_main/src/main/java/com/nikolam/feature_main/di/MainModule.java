package com.nikolam.feature_main.di;

import com.nikolam.feature_main.data.MainRepositoryImpl;
import com.nikolam.feature_main.data.MainRetrofitService;
import com.nikolam.feature_main.domain.MainRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import retrofit2.Retrofit;

@Module(includes = MainModule.repositoryBinder.class)
@InstallIn(ViewModelComponent.class)
public class MainModule {

    @Provides
    public MainRetrofitService provideMainRetrofitService(
            Retrofit retrofit
    ) {
        return retrofit.create(MainRetrofitService.class);
    }

    @Module
    @InstallIn(ViewModelComponent.class)
    interface repositoryBinder {
        @Binds
        MainRepository bindMainRepository(MainRepositoryImpl repository);
    }

};
