package com.nikolam.feature_post_detail.data;

import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.common.models.CommentDomainModel;
import com.nikolam.common.models.PostDomainModel;
import com.nikolam.data.db.AppRepository;
import com.nikolam.data.db.models.PostDataModel;
import com.nikolam.data.models.CommentNetworkModel;
import com.nikolam.data.models.ModelMappers;
import com.nikolam.data.models.PostNetworkModel;
import com.nikolam.feature_post_detail.data.models.PostCommentResponse;
import com.nikolam.feature_post_detail.domain.PostDetailRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import timber.log.Timber;

public class PostDetailRepositoryImpl implements PostDetailRepository {

    private final AppRepository appRepository;
    private final PostDetailRetrofitService retrofitService;
    private final ThreadExecutor threadExecutor;
    private final String userID;

    @Inject
    public PostDetailRepositoryImpl(AppRepository appRepository, PostDetailRetrofitService retrofitService, ThreadExecutor threadExecutor, @Named("userID") String userID) {
        this.appRepository = appRepository;
        this.retrofitService = retrofitService;
        this.threadExecutor = threadExecutor;
        this.userID = userID;
    }

    @Override
    public @NonNull Observable<PostDomainModel> getPost(String postID) {

        PublishSubject<PostDomainModel> observable = PublishSubject.create();

        //If the model is locally in the DB, return it otherwise fetch from the server
        Maybe<PostDataModel> localMaybe = appRepository.getPostByObjectID(postID);
        localMaybe.subscribeOn(Schedulers.from(threadExecutor)).subscribeWith(
                new MaybeObserver<PostDataModel>() {
                    PostDataModel model;

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(com.nikolam.data.db.models.@NonNull PostDataModel model) {
                        this.model = model;
                        onComplete();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.d("On error from localdb %s", e.getLocalizedMessage());
                        observable.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        if (model != null) {
                            Timber.d("Fetch post from db");
                            PostDomainModel domainModel = ModelMappers.postDataToDomainModel(model);
                            domainModel.setUserID(userID);
                            observable.onNext(domainModel);
                            observable.onComplete();
                            return;
                        } else {
                            Single<PostNetworkModel> remoteObservable = retrofitService.getPostByID(postID);
                            remoteObservable.subscribeWith(new SingleObserver<PostNetworkModel>() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {

                                }

                                @Override
                                public void onSuccess(@NonNull PostNetworkModel postNetworkModel) {
                                    Timber.d("Fetch post from server");
                                    observable.onNext(ModelMappers.postNetworkToDomainModel(postNetworkModel));
                                    observable.onComplete();
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Timber.d("On error from server %s", e.getLocalizedMessage());
                                    observable.onError(e);
                                }
                            });
                        }
                    }
                }
        );

        return observable;
        // return source.map(PostDomainModel::dataToDomainModel);
    }

    @Override
    public @NonNull Observable<PostCommentResponse> postComment(String postID, String userID, String comment) {
        return retrofitService.postComment(new Comment(postID, userID, comment)).toObservable();
    }

    @Override
    public @NonNull Observable<List<CommentDomainModel>> updateComments(String postID) {
        PublishSubject<List<CommentDomainModel>> publishSubject = PublishSubject.create();

        retrofitService.getComments(postID).subscribeWith(
                new SingleObserver<List<CommentNetworkModel>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<CommentNetworkModel> commentNetworkModels) {
                        ArrayList<CommentDomainModel> domainModels = new ArrayList<>();
                        for (CommentNetworkModel m : commentNetworkModels){
                            domainModels.add(ModelMappers.commentNetworkToDomainModel(m));
                        }
                        publishSubject.onNext(domainModels);
                        publishSubject.onComplete();
                        appRepository.updateComments(postID, commentNetworkModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        publishSubject.onError(e);

                    }
                }
        );

        return publishSubject;
    }

    public class Comment {
        private final String postID;
        private final String userID;
        private final String comment;

        private Comment(String postID, String userID, String comment) {
            this.postID = postID;
            this.userID = userID;
            this.comment = comment;
        }

        public String getPostID() {
            return postID;
        }

        public String getUserID() {
            return userID;
        }

        public String getComment() {
            return comment;
        }
    }
}
