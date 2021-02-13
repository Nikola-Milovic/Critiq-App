/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nikolam.common.domain;

import com.nikolam.common.domain.executor.PostExecutionThread;
import com.nikolam.common.domain.executor.ThreadExecutor;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class UseCase<T, Params> {

  private final ThreadExecutor threadExecutor;
  private final PostExecutionThread postExecutionThread;
  private final CompositeDisposable disposables;

  public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
    this.disposables = new CompositeDisposable();
  }

  protected abstract Observable<T> buildUseCaseObservable(Params params);

   public void execute(DisposableObserver<T> observer, Params params) {
    assert observer!=null;
    final Observable<T> observable = this.buildUseCaseObservable(params)
        .subscribeOn(Schedulers.from(threadExecutor))
        .observeOn(postExecutionThread.getScheduler());
    addDisposable(observable.subscribeWith(observer));
  }

  public void dispose() {
    if (!disposables.isDisposed()) {
      disposables.dispose();
    }
  }

  private void addDisposable(Disposable disposable) {
    assert disposable != null;
    assert disposables != null;
    disposables.add(disposable);
  }
}
