package com.zyj.features.home;

import android.support.v7.app.AppCompatActivity;

import com.zyj.qualifier.ActivityScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 张垚杰 on 2018/1/29.
 */
@Module
public class HomeModule {
    private final HomeMainActivity activity;

    public HomeModule(HomeMainActivity activity){
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public HomeMainActivity provideActivity(){
        return activity;
    }

}
