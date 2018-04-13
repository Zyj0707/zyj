package com.zyj.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zyj.mvp.component.BaseComponent;
import com.zyj.mvp.rx.BaseRxPresenter;
import com.zyj.mvp.ui.fragment.TransactionCommitter;
import com.zyj.mvp.ui.presenter.ExtendPresenter;

/**
 * Created by 张垚杰 on 2018/1/29.
 */

public abstract class ExtendMvpActivity<P extends BaseRxPresenter<ExtendPresenter.ExtendView, ExtendPresenter.ExtendCallbacks>,
        C extends BaseComponent<ExtendPresenter.ExtendView, ExtendPresenter.ExtendCallbacks, P>>
        extends BaseMvpActivity<ExtendPresenter.ExtendView, ExtendPresenter.ExtendCallbacks, P, C>
        implements ExtendPresenter.ExtendCallbacks, TransactionCommitter{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mPresenter instanceof ExtendPresenter) {
            ((ExtendPresenter)mPresenter).setHostCallbacks(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter instanceof ExtendPresenter) {
            ((ExtendPresenter)mPresenter).setHostCallbacks(null);
        }
    }
}
