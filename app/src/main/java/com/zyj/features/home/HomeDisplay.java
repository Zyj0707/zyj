package com.zyj.features.home;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.zyj.mvp.ui.dispaly.BaseDisplay;
import com.zyj.mvp.util.Preconditions;

import javax.inject.Inject;

/**
 * Created by 张垚杰 on 2018/1/29.
 */

public class HomeDisplay implements BaseDisplay{

    private final HomeMainActivity mainActivity;
    private Toolbar mToolbar;
    private HomeView mHomeView;

    @Inject
    public HomeDisplay(HomeMainActivity activity){
        Preconditions.chekNotNull(activity,"activity cannot be null");
        mainActivity = activity;
        mHomeView = activity;
    }


    @Override
    public void finish() {
        mainActivity.finish();

    }

    @Override
    public void showUpNavigation(boolean show) {
        final ActionBar actionBar = mainActivity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(show);
            actionBar.setHomeButtonEnabled(show);
        }

    }

    @Override
    public void setActionBarTitle(String title) {
        mainActivity.setTitle(title);

    }

    @Override
    public void setSupportActionBar(Object toolbar) {
        mToolbar = (Toolbar) toolbar;

        if(mToolbar != null) {
            mainActivity.setSupportActionBar(mToolbar);

            if(mainActivity.getSupportActionBar() != null) {
                mainActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
    }

    void showResult(String s){
        Log.i("repo", "text: 5"+s);
        mHomeView.showResult(s);
    }

    void showError(String msg){
        mHomeView.showError(msg);
    }
}
