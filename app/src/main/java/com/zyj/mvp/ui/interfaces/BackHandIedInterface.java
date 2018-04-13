package com.zyj.mvp.ui.interfaces;

import com.zyj.mvp.ui.fragment.BaseMvpFragment;
import com.zyj.mvp.ui.presenter.BasePresenter;
import com.zyj.mvp.ui.view.BaseView;

/**
 * Created by 张垚杰 on 2018/1/26.
 */

public interface BackHandIedInterface<V extends BaseView<VC>, VC, P extends BasePresenter<V, VC>> {

    void setSelectedFragment(BaseMvpFragment<V, VC, P> backHandledFragment);
}
