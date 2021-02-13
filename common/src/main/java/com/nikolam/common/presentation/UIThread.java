package com.nikolam.common.presentation;

import com.nikolam.common.domain.executor.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;

@Singleton
public class UIThread implements PostExecutionThread {

  @Inject
  UIThread() {}

  @Override public Scheduler getScheduler() {
    return AndroidSchedulers.mainThread();
  }
}
