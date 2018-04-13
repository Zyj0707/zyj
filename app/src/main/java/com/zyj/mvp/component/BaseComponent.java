package com.zyj.mvp.component;

import android.support.annotation.NonNull;

import com.zyj.mvp.ui.presenter.BasePresenter;
import com.zyj.mvp.ui.view.BaseView;

/**
 * Created by 张垚杰 on 2018/1/26.
 */

public interface BaseComponent<V extends BaseView, VC, P extends BasePresenter<V, VC>> {

    @NonNull
    P presenter();
}
