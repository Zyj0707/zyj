package com.zyj.modules.provider;


import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by 张垚杰 on 2018/2/1.
 */
@Module
public class ProviderModule {
    private static final int CONNECT_TIME_OUT = 3;
    private static final int SOCKET_TIME_OUT = 30;

    @Singleton
    @Provides
    Gson provideGson(final GsonConfig config){
        final GsonBuilder builder = new GsonBuilder();
        if(config.autoGsonTypeAdapterFactory() != null){
            builder.registerTypeAdapterFactory(config.autoGsonTypeAdapterFactory());
        }
        return builder.setPrettyPrinting()
                .create();
    }

    @Singleton
    @Provides
    OkHttpClient provideHttpClient(final Context context, final HttpClientConfig config){
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(SOCKET_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(SOCKET_TIME_OUT, TimeUnit.SECONDS);

        if (config.enableLog()) {
            builder.addNetworkInterceptor(new StethoInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(final String message) {
                            Timber.tag("OkHttp").d(message);
                        }
                    }).setLevel(HttpLoggingInterceptor.Level.BODY));
        }


        return builder.build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(final RetrofitConfig config, final OkHttpClient okHttpClient, final Gson gson){
        return new Retrofit.Builder().baseUrl(config.baseUrl())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

}
