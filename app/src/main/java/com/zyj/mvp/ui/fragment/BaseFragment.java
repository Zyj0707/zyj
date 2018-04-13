package com.zyj.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyj.mvp.ui.activity.BaseMvpActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张垚杰 on 2018/1/26.
 */

public abstract class BaseFragment extends SafelySupportFragment{
    private Unbinder mUnBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    protected abstract int getLayoutRes();

    protected boolean shouldHaveOptionsMenu() {
        return false;
    }

    protected boolean autoBindViews() {
        return true;
    }

    @CallSuper
    protected void bindView(final View rootView) {
        if (autoBindViews()) {
            mUnBinder = ButterKnife.bind(this, rootView);
        }
    }

    protected void bindData(){}

    protected void unbindView() {
        if (autoBindViews() && mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    protected final void setSupportActionBar(Toolbar toolbar) {
        ((BaseMvpActivity) getActivity()).setSupportActionBar(toolbar);
    }
}
