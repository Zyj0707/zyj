package com.zyj;

import android.app.Application;
import android.support.annotation.NonNull;

import com.zyj.modules.AppComponent;
import com.zyj.modules.AppModule;
import com.zyj.modules.DaggerAppComponent;
import com.zyj.modules.IApplication;

import java.util.Timer;

import timber.log.Timber;

/**
 * Created by 张垚杰 on 2018/1/29.
 */

public class AppContext extends Application implements IApplication{

    private AppComponent appComponent;
    private static AppContext appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        appComponent = createComponent();

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
        Timber.plant(new Timber.DebugTree());
    }

    protected AppComponent createComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppContext getInstance() {
        return appContext;
    }

    @NonNull
    @Override
    public AppComponent appComponent() {
        return appComponent;
    }
}
