package com.zyj.http.pdata;

/**
 * Created by 张垚杰 on 2018/2/1.
 */

public class PDataEmpty extends PData{

    public PDataEmpty(String method){
        super();
        this.method = method;
    }
    @Override
    protected void method() {

    }
}
