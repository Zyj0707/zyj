package com.zyj.mvp.ui.presenter;

import com.zyj.mvp.rx.BaseRxPresenter;
import com.zyj.mvp.ui.view.BaseView;

/**
 * Created by 张垚杰 on 2018/1/29.
 */

public abstract class ExtendPresenter extends BaseRxPresenter<ExtendPresenter.ExtendView,
        ExtendPresenter.ExtendCallbacks> {

    private ExtendCallbacks mCallbacks;

    @Override
    protected ExtendCallbacks createUiCallbacks(ExtendView view) {
        return new ExtendCallbacks() {
            @Override
            public void finish() {
                if(mCallbacks != null){
                    mCallbacks.finish();
                }
            }
        };
    }
    public void setHostCallbacks(ExtendCallbacks extendCallbacks) {
        mCallbacks = extendCallbacks;
    }

    public interface ExtendCallbacks {
        void finish();
    }

    public interface ExtendView extends BaseView<ExtendCallbacks>{}
}
