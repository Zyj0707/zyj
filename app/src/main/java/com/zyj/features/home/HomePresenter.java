package com.zyj.features.home;

import android.util.Log;

import com.squareup.otto.Subscribe;
import com.zyj.mvp.ui.presenter.ExtendPresenter;
import com.zyj.state.HomeState;

import javax.inject.Inject;

/**
 * Created by 张垚杰 on 2018/1/29.
 */

public class HomePresenter extends ExtendPresenter{

    private final Repo mRepo;
    private final HomeState mHomeState;

    @Inject
    HomePresenter(final HomeState homeState, final Repo repo){
        super();
        mHomeState = homeState;
        mRepo = repo;
    }

    @Subscribe
    public void onHomeResult(HomeState.HomeResultEvent event){
        HomeDisplay display = (HomeDisplay) getDisplay();
       // String s =event.item;
        display.showResult(event.item);
    }

    @Subscribe
    public void onHomeError(HomeState.HomeErrorEvent errorEvent){
        HomeDisplay display = (HomeDisplay) getDisplay();
        String s =errorEvent.item;
        display.showError(errorEvent.item);
    }

    @Override
    protected void onInited() {
        super.onInited();
        mHomeState.registerForEvent(this);
    }

    @Override
    protected void onSuspended() {
        super.onSuspended();
        mHomeState.unregisterForEvent(this);
    }

    void text(){
        Log.i("text", "text: 1");
        addUtilDestroy(mRepo.text());
    }
}
