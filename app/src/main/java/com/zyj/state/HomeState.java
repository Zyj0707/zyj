package com.zyj.state;

import android.support.annotation.MainThread;

import com.zyj.mvp.state.BaseState;
import com.zyj.mvp.ui.activity.BaseMvpActivity;

/**
 * Created by 张垚杰 on 2018/1/29.
 */

public interface HomeState extends BaseState{

    @MainThread
    void notifyHomeResult(String  data);

    @MainThread
    void notifyError(String error);

    class HomeResultEvent extends BaseArgumentEvent<String>{
        public HomeResultEvent(String ret){
            super(ret);
        }
    }

    class HomeErrorEvent extends BaseArgumentEvent<String>{
        public HomeErrorEvent(String ret){
            super(ret);
        }
    }


}
