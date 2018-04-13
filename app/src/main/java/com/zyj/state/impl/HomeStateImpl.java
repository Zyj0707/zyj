package com.zyj.state.impl;

import android.util.Log;

import com.squareup.otto.Bus;
import com.zyj.state.HomeState;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by 张垚杰 on 2018/1/29.
 */
@Singleton
public class HomeStateImpl implements HomeState{
    private final Bus mEventBus;

    @Inject
    public HomeStateImpl(final Bus bus){
        this.mEventBus = bus;
    }
    @Override
    public void registerForEvent(Object receiver) {
        mEventBus.register(receiver);
    }

    @Override
    public void unregisterForEvent(Object receiver) {
        mEventBus.unregister(receiver);

    }

    @Override
    public void notifyHomeResult(String data) {
        Log.i("repo", "text: 3");
        mEventBus.post(new HomeResultEvent(data));
    }

    @Override
    public void notifyError(String error) {
        mEventBus.post(new HomeErrorEvent(error));
    }
}
