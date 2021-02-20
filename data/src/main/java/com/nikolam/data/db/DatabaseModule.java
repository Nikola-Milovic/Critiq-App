package com.nikolam.data.db;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

@Module()
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "app-db").build();
    }

    @Provides
    AppRepository provideAppRepository(AppDatabase db, ThreadExecutor exe, PostExecutionThread pet){
        return new AppRepository(db, exe, pet);
    }
}