package com.nikolam.common.domain.di;


import com.nikolam.common.domain.executor.JobExecutor;
import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.common.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module(includes = DomainModule.binder.class)
@InstallIn(SingletonComponent.class)
public class DomainModule {

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Module
    @InstallIn(SingletonComponent.class)
    interface binder {
        @Binds
        public abstract ThreadExecutor bindThreadExecutor(JobExecutor executor);
    }

}
