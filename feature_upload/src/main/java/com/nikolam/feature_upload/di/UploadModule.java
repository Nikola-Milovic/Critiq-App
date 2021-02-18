package com.nikolam.feature_upload.di;

import com.nikolam.feature_upload.data.UploadRepositoryImpl;
import com.nikolam.feature_upload.data.UploadService;
import com.nikolam.feature_upload.domain.UploadRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import retrofit2.Retrofit;

@Module(includes = UploadModule.repositoryBinder.class)
@InstallIn(ViewModelComponent.class)
public class UploadModule {

    @Module
    @InstallIn(ViewModelComponent.class)
    interface repositoryBinder {
        @Binds
        UploadRepository bindUploadRepository(UploadRepositoryImpl repository);
    }

    @Provides
    public UploadService provideUploadService(
            Retrofit retrofit
    ) {
        return retrofit.create(UploadService.class);
    }

};
