package com.zyj.features.home;

import android.content.Context;
import android.util.Log;

import com.zyj.qualifier.ActivityScope;
import com.zyj.state.HomeState;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 张垚杰 on 2018/1/29.
 */
@ActivityScope
public class Repo {
    private final Context mContext;
    private final HomeState mHomeState;

    @Inject
    public Repo(final Context context, final HomeState homeState){
        mContext = context;
        mHomeState = homeState;
    }

    public Disposable text(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //e.onNext("123");
                e.onError(new Throwable("出错"));
            }
        }
    ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(str ->{
                    Log.i("repo", "text: 2");
                    mHomeState.notifyHomeResult(str);
                },throwable -> {
                    mHomeState.notifyError(throwable.getMessage());
                });
    }

//    public Observable<String> pay(){
//        return
//    }



}
