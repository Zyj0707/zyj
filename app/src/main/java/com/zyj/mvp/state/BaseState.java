package com.zyj.mvp.state;

import com.zyj.mvp.util.Preconditions;

/**
 * Created by 张垚杰 on 2018/1/29.
 */

public interface BaseState {

    void registerForEvent(Object receiver);

    void unregisterForEvent(Object receiver);

    class UiCausedEvent{
        public final int callingId;

        public UiCausedEvent(int callingId){
            this.callingId = callingId;
        }
    }

    class BaseArgumentEvent<T>{
        public final T item;

        public BaseArgumentEvent(T item){
            this.item = Preconditions.chekNotNull(item, "item cannot be null");
        }
    }
}
