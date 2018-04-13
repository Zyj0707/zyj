package com.zyj.mvp.ui.presenter;



import com.zyj.mvp.ui.dispaly.BaseDisplay;
import com.zyj.mvp.ui.view.BaseView;
import com.zyj.mvp.util.Preconditions;

/**
 * Created by 张垚杰 on 2018/1/26.
 */

public abstract class BasePresenterDelegate<V extends BaseView<VC>,VC, P extends BasePresenter<V,VC>> {

    private P mPresenter;

    public void onCreate(BaseDisplay display){
        mPresenter = createPresenter();
        checkPresenter();
        mPresenter.setDisplay(display);
        mPresenter.init();

    }

    public void onStart(){
        checkPresenter();
        mPresenter.resume();

    }

    public void onStop(){
        checkPresenter();
        mPresenter.pause();
    }

    public void attachView(V view){
        checkPresenter();
        mPresenter.attachView(view);
    }

    public void detachView(V view){
        checkPresenter();
        mPresenter.detachView(view);
    }

    public void onDestroy(){
        checkPresenter();
        mPresenter.suspend();
        mPresenter.setDisplay(null);
    }

    protected abstract P createPresenter();

    private void checkPresenter() {
        Preconditions.checkState(mPresenter != null, "You must call BasePresenterDelegate#onCreate! " +
                "And createPresenter must return non-null");
    }
}
