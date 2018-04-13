package com.zyj.mvp.ui.presenter;

import com.zyj.mvp.ui.view.BaseView;

/**
 * Created by 张垚杰 on 2018/1/26.
 */

public interface GetPresenterDelegate<V extends BaseView<VC>, VC, P extends BasePresenter<V, VC>> {

    BasePresenterDelegate<V, VC, P> getPresenterDelegate();
}
