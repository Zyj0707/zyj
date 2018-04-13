package com.zyj.mvp.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张垚杰 on 2018/1/26.
 */

public abstract class BaseActivity extends SafelyAppCompatActivity{

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        bindData();
        bindView();
    }

    protected void setDisplayBack() {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindView();
    }

    protected boolean autoBindViews(){
        return true;
    }

    @CallSuper
    protected void bindView(){
        if(autoBindViews()){
            mUnBinder = ButterKnife.bind(this);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    protected void unbindView(){
        if(autoBindViews() && mUnBinder != null){
            mUnBinder.unbind();
        }
    }

    protected void bindData() {}

    protected abstract int getLayoutRes();

    public final void finishTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.finishAfterTransition();
        }
        else {
            this.finish();
        }
    }

    private Bundle provideOptionsBundle(Pair<View, String>[] pairs) {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs).toBundle();
    }

}
