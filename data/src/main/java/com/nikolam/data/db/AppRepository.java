package com.nikolam.data.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.annotations.SerializedName;
import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.data.db.models.PostDataModel;
import com.nikolam.data.models.CommentNetworkModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import timber.log.Timber;

@Singleton
public class AppRepository {

    private final AppDatabase db;
    private final ThreadExecutor threadExecutor;
    private final Context context;
    private final DataRetrofitService dataRetrofitService;

    @Inject
    public AppRepository(AppDatabase db, ThreadExecutor threadExecutor,
                         PostExecutionThread postExecutionThread, @ApplicationContext Context context, DataRetrofitService dataRetrofitService) {
        this.db = db;
        this.threadExecutor = threadExecutor;
        this.context = context;
        this.dataRetrofitService = dataRetrofitService;
    }

    public void insertPost(PostDataModel post) {
        db.postDao().insertPost(post).subscribeOn(
                Schedulers.from(threadExecutor)
        ).subscribe();
    }

    public Observable<List<PostDataModel>> getPosts() {
        return db.postDao().getMyPosts();
    }

    public Maybe<PostDataModel> getPostByObjectID(String id) {
        return db.postDao().getPostByObjectID(id);
    }

    public void updateComments(String postID, List<CommentNetworkModel> comments) {
        db.postDao().updatePostComments(postID, comments);

    }

    public void saveFCMToken(String userID) {

        SharedPreferences pref = context.getSharedPreferences("Firebase", Context.MODE_PRIVATE);

        String token = pref.getString("fcm_token", null);

        Timber.d("Save FCM Token %s, for user  %s", token, userID);

        if (token != null) {
            dataRetrofitService.saveToken(userID, new FCMTokenMessage(token)).subscribeOn(Schedulers.from(threadExecutor)).subscribeWith(new CompletableObserver() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onComplete() {

                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Timber.d(e);
                }
            });
        }
    }

    class FCMTokenMessage {
        @SerializedName("fcm_token")
        public final String FCMToken;
        public FCMTokenMessage(String fcmToken) {
            FCMToken = fcmToken;
        }
    }
}