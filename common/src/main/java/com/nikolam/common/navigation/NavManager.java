package com.nikolam.common.navigation;

import android.net.Uri;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import javax.inject.Inject;

public class NavManager {

    private Consumer<Uri> navEventListener = null;
    private Consumer<Void> popBackStackListener = null;

    @Inject
    public NavManager() {
    }

    public void navigate(final Uri uri) {
        Optional.ofNullable(this.navEventListener).ifPresent(it -> it.accept(uri));
    }

    public void setOnNavEvent(final Consumer<Uri> navEventListener) {
        this.navEventListener = Objects.requireNonNull(navEventListener);
    }

    public void popBackStack() {
        popBackStackListener.accept(null);
    }

    public void setPopBackStack(final Consumer<Void> popBackStackListener) {
        this.popBackStackListener = Objects.requireNonNull(popBackStackListener);
    }
//    private void navEventListener = (Uri uri);
//
//    private var popBackStackListener:(()->Unit)?=null
//
//    private void  navigate(uri:Uri) {
//        navEventListener ?.invoke(uri)
//    }
//
//    private void  setOnNavEvent(navEventListener:(uri:Uri) ->Unit)
//
//    {
//        this.navEventListener = navEventListener
//    }
//
//    private void  popBackStack() {
//        popBackStackListener?.invoke()
//    }
//
//    private void setPopBackStack(popBackStackListener:() ->Unit)
//
//    {
//        this.popBackStackListener = popBackStackListener
//    }
}