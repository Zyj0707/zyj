package com.zyj.http.rdata;

import android.os.Parcel;

/**
 * Created by 张垚杰 on 2018/2/1.
 */

public class RData {
    private int result;
    private String msg;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    protected void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.result);
        dest.writeString(this.msg);
    }

    protected RData(Parcel in) {
        this.result = in.readInt();
        this.msg = in.readString();
    }
}
