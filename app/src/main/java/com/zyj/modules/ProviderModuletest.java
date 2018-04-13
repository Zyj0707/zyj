package com.zyj.modules;

import com.squareup.otto.Bus;
import com.zyj.state.HomeState;
import com.zyj.state.impl.HomeStateImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 张垚杰 on 2018/1/29.
 */
@Module
public class ProviderModuletest {



    @Singleton
    @Provides
    Bus provideRxBus(){
        return new Bus();
    }

    @Singleton
    @Provides
    HomeState provideHomeState(final HomeStateImpl homeState){
        return homeState;
    }
}
