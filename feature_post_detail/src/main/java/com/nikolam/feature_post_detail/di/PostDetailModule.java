package com.nikolam.feature_post_detail.di;

import com.nikolam.feature_post_detail.data.PostDetailRepositoryImpl;
import com.nikolam.feature_post_detail.data.PostDetailRetrofitService;
import com.nikolam.feature_post_detail.domain.PostDetailRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import retrofit2.Retrofit;

@Module(includes = PostDetailModule.repositoryBinder.class)
@InstallIn(ViewModelComponent.class)
public class PostDetailModule {

    @Provides
    public PostDetailRetrofitService providePostDetailRetrofitService(
            Retrofit retrofit
    ) {
        return retrofit.create(PostDetailRetrofitService.class);
    }

    @Module
    @InstallIn(ViewModelComponent.class)
    interface repositoryBinder {
        @Binds
        PostDetailRepository bindPostDetailRepository(PostDetailRepositoryImpl repository);
    }

};
