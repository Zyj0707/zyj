package com.zyj.mvp.ui.dispaly;

/**
 * Created by 张垚杰 on 2018/1/26.
 */

public interface BaseDisplay {

    void finish();

    void showUpNavigation(boolean show);

    void setActionBarTitle(String title);

    void setSupportActionBar(Object toolbar);
}
