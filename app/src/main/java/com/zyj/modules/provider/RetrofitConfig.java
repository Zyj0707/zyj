package com.zyj.modules.provider;

import com.google.auto.value.AutoValue;


/**
 * Created by 张垚杰 on 2018/2/1.
 */
@AutoValue
public abstract class RetrofitConfig {
    public static Builder builder(){
        return new AutoValue_RetrofitConfig.Builder();
    }

    public abstract String baseUrl();

    @AutoValue.Builder
    public abstract static class Builder{
        public abstract Builder baseUrl(final String baseUrl);

        public abstract RetrofitConfig build();
    }
}
