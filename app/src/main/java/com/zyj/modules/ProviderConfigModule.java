package com.zyj.modules;

import com.zyj.BuildConfig;
import com.zyj.modules.provider.HttpClientConfig;
import com.zyj.modules.provider.RetrofitConfig;
import com.zyj.util.Constant;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 张垚杰 on 2018/2/1.
 */
@Module
public class ProviderConfigModule {
    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final int RETRY = 3;

    @Singleton
    @Provides
    HttpClientConfig provideHttpClientConfig(){
        return HttpClientConfig.builder().enableLog(DEBUG)
                .retryTimeout(RETRY)
                .build();
    }

    @Singleton
    @Provides
    RetrofitConfig provideRetrofitConfig(){
        return RetrofitConfig.builder()
                .baseUrl(Constant.BASE_URL).build();
    }

}
