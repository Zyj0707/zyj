package com.zyj.mvp.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.zyj.modules.ActModule;
import com.zyj.mvp.component.BaseComponent;
import com.zyj.mvp.ui.fragment.SupportFragmentTransactionDelegate;
import com.zyj.mvp.ui.fragment.TransactionCommitter;
import com.zyj.mvp.ui.presenter.BasePresenter;
import com.zyj.mvp.ui.view.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张垚杰 on 2018/1/26.
 */

public abstract class BaseMvpActivity<V extends BaseView<VC>,VC, P extends BasePresenter<V,VC>,
        C extends BaseComponent<V, VC, P>> extends MvpDiActivity<V, VC, P, C> implements
        TransactionCommitter{

    private final SupportFragmentTransactionDelegate mSupportFragmentTransactionDelegate
            = new SupportFragmentTransactionDelegate();
    private volatile boolean mIsResumed;
    private boolean hadIntercept;
    private Unbinder mUnBinder;
    private Toolbar mToolbar;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsResumed = true;
        setContentView(getLayoutRes());
        bindData();
        bindView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsResumed = false;
    }

    protected boolean safeCommit(@NonNull final FragmentTransaction transaction){
        return mSupportFragmentTransactionDelegate.safeCommit(this, transaction);
    }

    public void setTitle(String text){
        if(mTitle != null){
            mTitle.setText(text);
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        mIsResumed = true;
        mSupportFragmentTransactionDelegate.onResumed();
    }

    @Override
    public boolean isCommitterResumed() {
        return mIsResumed;
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

    }

    protected void unbindView(){
        if(autoBindViews() && mUnBinder != null){
            mUnBinder.unbind();
        }
    }

    protected void bindData(){}

    protected abstract int getLayoutRes();

    public final boolean startActivitySafely(final Intent intent) {
        return StartActivityDelegate.startActivitySafely(this, intent, provideOptionsBundle(null));
    }

    public final boolean startActivitySafely(final Intent intent, Pair<View, String>[] pairs) {
        return StartActivityDelegate.startActivitySafely(this, intent, provideOptionsBundle(pairs));
    }

    public final void finishTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.finishAfterTransition();
        }
        else {
            this.finish();
        }
    }

    protected ActModule getActModule(){
        return new ActModule(this);
    }

    private Bundle provideOptionsBundle(Pair<View, String>[] pairs) {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs).toBundle();
    }
}
