package com.nikolam.feature_my_posts.di;

import com.nikolam.feature_my_posts.data.MyPostsRepositoryImpl;
import com.nikolam.feature_my_posts.domain.MyPostsRepository;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module(includes = MyPostsModule.repositoryBinder.class)
@InstallIn(ViewModelComponent.class)
public class MyPostsModule {

    @Module
    @InstallIn(ViewModelComponent.class)
    interface repositoryBinder {
        @Binds
        MyPostsRepository bindMyPostsRepository(MyPostsRepositoryImpl repository);
    }

//    @Provides
//    public AuthService provideAuthService(
//            Retrofit retrofit
//    ) {
//        return retrofit.create(AuthService.class);
//    }

};
