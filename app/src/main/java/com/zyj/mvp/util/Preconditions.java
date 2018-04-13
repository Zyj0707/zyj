package com.zyj.mvp.util;

/**
 * Created by 张垚杰 on 2018/1/26.
 */

public class Preconditions {

    public static <T> T chekNotNull(T object, String errorMsg){
        if(object == null){
            throw new NullPointerException(errorMsg);
        }
        return object;
    }

    public static void checkState(boolean state, String errorMsg){
        if(!state){
            throw new IllegalStateException(errorMsg);
        }
    }

    public static void checkArgument(boolean state, String errorMsg){
        if(!state){
            throw new IllegalStateException(errorMsg);
        }
    }
}
