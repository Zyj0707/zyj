package com.zyj.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyj.mvp.ui.activity.BaseMvpActivity;
import com.zyj.mvp.ui.activity.StartActivityDelegate;
import com.zyj.mvp.ui.interfaces.BackHandIedInterface;
import com.zyj.mvp.ui.presenter.BasePresenter;
import com.zyj.mvp.ui.view.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张垚杰 on 2018/1/26.
 */

public abstract class BaseMvpFragment<V extends BaseView<VC>, VC, P extends BasePresenter<V, VC>>
        extends MvpFragment<V, VC, P> implements TransactionCommitter{

    private final SupportFragmentTransactionDelegate mSupportFragmentTransactionDelegate
            = new SupportFragmentTransactionDelegate();
    protected BackHandIedInterface<V, VC, P> mBackHandIedInterface;
    private Unbinder mUnBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if((getActivity() instanceof BackHandIedInterface)){
            this.mBackHandIedInterface = (BackHandIedInterface)getActivity();
        }
        bindData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(shouldHaveOptionsMenu());
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbindView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mSupportFragmentTransactionDelegate.onResumed();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mBackHandIedInterface != null){
            mBackHandIedInterface.setSelectedFragment(this);
        }
    }

    @Override
    public void onStop() {
        if(mBackHandIedInterface !=null){
            mBackHandIedInterface.setSelectedFragment(null);
            mBackHandIedInterface = null;
        }
        super.onStop();
    }

    @Override
    public boolean isCommitterResumed() {
        return isResumed();
    }

    protected boolean startActivitySafely(final Intent intent) {
        return StartActivityDelegate.startActivitySafely(this, intent);
    }

    protected final boolean startActivityForResultSafely(final Intent intent, final int code) {
        return StartActivityDelegate.startActivityForResultSafely(this, intent, code);
    }

    protected boolean safeCommit(@NonNull FragmentTransaction transaction) {
        return mSupportFragmentTransactionDelegate.safeCommit(this, transaction);
    }

    protected abstract int getLayoutRes();

    protected boolean shouldHaveOptionsMenu() {
        return false;
    }

    protected boolean autoBindViews(){
        return true;
    }

    @CallSuper
    protected void bindView(final View rootView){
        if(autoBindViews()){
            mUnBinder = ButterKnife.bind(this, rootView);
        }
    }

    protected void bindData(){}

    protected void unbindView(){
        if(autoBindViews() && mUnBinder != null){
            mUnBinder.unbind();
        }
    }

    @Override
    public boolean isModal() {
        return false;
    }

    public boolean onBackPressed(){
        return false;
    }

    protected final void setSupportActionBar(Toolbar toolbar) {
        ((BaseMvpActivity) getActivity()).setSupportActionBar(toolbar);
    }
}
