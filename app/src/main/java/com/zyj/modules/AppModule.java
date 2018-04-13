package com.zyj.modules;

import android.app.Application;
import android.content.Context;

import com.zyj.features.home.HomeMainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 张垚杰 on 2018/1/29.
 */
@Module
public class AppModule {

    private final Application application;

    public AppModule(final Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplicationContext(){
        return application;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return application;
    }

}
