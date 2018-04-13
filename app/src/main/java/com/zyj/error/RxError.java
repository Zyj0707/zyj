package com.zyj.error;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * Created by 张垚杰 on 2018/1/30.
 */
@AutoValue
public abstract class RxError {

    public abstract String message();

    @Nullable
    public abstract String type();

    @Nullable
    public abstract List<Detail> errors();

    @AutoValue
    public abstract static class Detail {

        public abstract String resource();

        public abstract String field();

        public abstract String code();

    }
//
//    @NonNull
//    public static Builder builder() {
//        return new AutoValue_RxError.Builder();
//    }
//
//    @AutoValue.Builder
//    public abstract static class Builder {
//
//        public abstract Builder message(@NonNull String message);
//
//        public abstract Builder type(@Nullable String type);
//
//        public abstract Builder errors(@Nullable List<Detail> errors);
//
//        public abstract RxError build();
//    }
}
