package com.zyj.http.pdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 张垚杰 on 2018/2/1.
 */

public abstract class PData {
    protected String method;
    private JSONObject body;
    private JSONObject pdata;

    public PData(){
        pdata = new JSONObject();
        body = new JSONObject();
        method();
    }

    public String getPStr()throws JSONException{
        pdata.put("method", method);
        pdata.put("params", body);
        return pdata.toString();
    }

    public void bodyAdd(String key, int value){
        try {
            body.put(key, value);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void bodyAdd(String key, long value){
        try{
            body.put(key, value);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void bodyAdd(String key, String value){
        try {
            if(value != null){
                body.put(key, value);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void bodyAdd(String key, boolean value) {
        try {
            body.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void bodyAdd(String key, JSONObject value) {
        try {
            body.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void bodyAdd(String key, JSONArray elementList) {
        try {
            body.put(key, elementList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected abstract void method();
}
