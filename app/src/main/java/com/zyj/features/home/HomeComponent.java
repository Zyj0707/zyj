package com.zyj.features.home;

import com.zyj.modules.ActModule;
import com.zyj.mvp.component.BaseComponent;
import com.zyj.mvp.ui.presenter.ExtendPresenter;
import com.zyj.qualifier.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by 张垚杰 on 2018/1/29.
 */
@ActivityScope
@Subcomponent(modules = {
        ActModule.class
})
public interface HomeComponent extends BaseComponent<ExtendPresenter.ExtendView,
        ExtendPresenter.ExtendCallbacks, HomePresenter>{

    //void inject(HomeMainActivity activity);

}
