package com.zyj.modules.provider;

import com.google.auto.value.AutoValue;

/**
 * Created by 张垚杰 on 2018/2/1.
 */
@AutoValue
public abstract class HttpClientConfig {
    public static Builder builder(){
        return new AutoValue_HttpClientConfig.Builder();
    }

    public abstract boolean enableLog();

    public abstract int retryTimeout();

    @AutoValue.Builder
    public abstract static class Builder{

        public abstract Builder enableLog(final boolean enableLog);

        public abstract Builder retryTimeout(final int retry);

        public abstract HttpClientConfig build();
    }
}
