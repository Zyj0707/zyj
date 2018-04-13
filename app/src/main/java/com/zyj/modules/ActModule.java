package com.zyj.modules;

import android.support.v7.app.AppCompatActivity;

import com.zyj.features.home.HomeDisplay;
import com.zyj.qualifier.ActivityScope;
import com.zyj.state.HomeState;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 张垚杰 on 2018/1/29.
 */
@Module
public class ActModule {
    private final AppCompatActivity activity;

    public ActModule(AppCompatActivity activity){
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public AppCompatActivity provideActivity(){
        return activity;
    }


}
