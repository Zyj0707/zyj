package com.zyj.mvp.ui.view;

/**
 * Created by 张垚杰 on 2018/1/26.
 */

public interface BaseView<VC> {
    void setCallbacks(VC callbacks);

    boolean isModal();
}
