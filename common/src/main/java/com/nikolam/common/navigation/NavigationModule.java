package com.nikolam.common.navigation;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class NavigationModule {

    @Provides
    @Singleton
    public static NavManager provideNavManager() {
        return new NavManager();
    }

}