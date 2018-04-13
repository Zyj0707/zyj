package com.zyj.features.text;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxSeekBar;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.zyj.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by 张垚杰 on 2018/2/2.
 */

public class RxbindingActivity extends AppCompatActivity{

    @BindView(R.id.edi_name)
    EditText editText;
    @BindView(R.id.tv_name)
    TextView textView;
    @BindView(R.id.bt_sumbit)
    Button button;
    @BindView(R.id.color_slider)
    SeekBar seekBar;
    @BindView(R.id.rxbind_layout)
    LinearLayout linearLayout;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxbinding_main);
        ButterKnife.bind(this);

        rxbind();

    }

    public void rxbind(){
        compositeDisposable = new CompositeDisposable();
//        final EditText editText = findViewById(R.id.edi_name);
//        final TextView textView = findViewById(R.id.tv_name);
        Disposable editTextSub =
                RxTextView.textChanges(editText)
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(CharSequence charSequence) throws Exception {
                        return new StringBuilder(charSequence).reverse().toString();
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((str)->{
                      textView.setText(str);
                });
        compositeDisposable.add(editTextSub);

        Observable clickObservable = RxView.clicks(button).share();//将按钮的监听分享

        Disposable buttonDis =
                clickObservable.subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        textView.setText("Button was click");
                    }
                });
        compositeDisposable.add(buttonDis);

        Disposable buttonDis1 =
                clickObservable.subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Timber.d("Button was click!!");
                    }
                });
        compositeDisposable.add(buttonDis1);

        Disposable seekBarDis =
                RxSeekBar.userChanges(seekBar)
                .subscribe(seekValue ->{
                    linearLayout.setBackgroundColor(Color.argb(seekValue,131,255,8));

                });
        compositeDisposable.add(seekBarDis);



    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}
