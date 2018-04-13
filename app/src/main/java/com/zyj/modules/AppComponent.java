package com.zyj.modules;

import com.zyj.features.home.HomeComponent;
import com.zyj.features.home.HomeModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 张垚杰 on 2018/1/29.
 */
@Singleton
@Component(modules = {AppModule.class,
                      ProviderModuletest.class
       })
public interface AppComponent {

    HomeComponent homecomponent(ActModule actModule);

   // HomeComponent homecomponent1(HomeModule homeModule);

}
