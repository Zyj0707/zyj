package com.zyj.features.home;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zyj.AppContext;
import com.zyj.Book;
import com.zyj.BookManager;
import com.zyj.R;
import com.zyj.mvp.ui.activity.ExtendMvpActivity;
import com.zyj.service.LocalService;

import java.lang.ref.WeakReference;
import java.util.List;


import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 张垚杰 on 2018/1/29.
 */

public class HomeMainActivity extends ExtendMvpActivity<HomePresenter, HomeComponent> implements
        HomeView{

    @BindView(R.id.text_bt)
    Button button;
    @BindView(R.id.text_tv)
    TextView textView;
    @BindView(R.id.ivBattery)
    ImageView ivBattery;

    private BatteryChangeReceiver mBatteryReceiver;

    private BookManager mBookManager = null;

    LocalService mService;

    //标志当前与服务端连接状况的布尔值
    private boolean mBound = false;
    private List<Book> mBooks;

//    @Inject
//    HomeDisplay homeDisplay;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(getLocalClassName(), "service connected");
            mBookManager = BookManager.Stub.asInterface(service);
            mBound = true;
            if(mBookManager != null){
                try{
                    mBooks = mBookManager.getBooks();
                    Log.e(getLocalClassName(), mBooks.toString());
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(getLocalClassName(), "service disconnected");
            mBound = false;

        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(getLocalClassName(), "service connected");
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        button.setOnClickListener(v -> {
            //mPresenter.text();
            //addBook();
            int num = mService.getRandomNumber();
            Toast.makeText(this, "number: "+num, Toast.LENGTH_SHORT).show();

        });

        mBatteryReceiver = new BatteryChangeReceiver(this);
        IntentFilter batteryFilter = new IntentFilter();
        batteryFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBatteryReceiver, batteryFilter);//注册电池电量更新的广播

    }

    public void addBook(){
        if(!mBound){
            attemptToBindService();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if(mBookManager == null){
            return;
        }
        Book book = new Book();
        book.setName("Java");
        book.setPrice(30);
        try{
            mBookManager.addBook(book);
            Log.e(getLocalClassName(), book.toString() );
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    private void attemptToBindService(){
        Intent intent = new Intent();
        intent.setAction("com.zyj.aidl");
        intent.setPackage("com.zyj");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!mBound){
           // attemptToBindService();
            Intent intent = new Intent(this, LocalService.class);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mBound){
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBatteryReceiver != null) {
            unregisterReceiver(mBatteryReceiver);
        }
    }

    @Override
    public void showResult(String s) {
        Log.i("repo", "text: 6");
        textView.setText(s);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initializDisplay() {
        display = new HomeDisplay(this);
        //display = homeDisplay;
    }

    @Override
    protected void initializeDependence() {
        component = AppContext.getInstance().appComponent().homecomponent(getActModule());
//        AppContext.getInstance().appComponent().homecomponent1(new HomeModule(this)).inject(this);
//        component = AppContext.getInstance().appComponent().homecomponent1(new HomeModule(this));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.text_main;
    }


    //电池电量更新广播
    private static class BatteryChangeReceiver extends BroadcastReceiver {
        private WeakReference<HomeMainActivity> reference;

        BatteryChangeReceiver(HomeMainActivity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                // 获取当前电量
                int level = intent.getIntExtra("level", 0);
                // 电量的总刻度
                int scale = intent.getIntExtra("scale", 0);
                // 把它转换成百分比
                int key = ((level * 100) / scale);

                if (reference.get() != null && reference.get().ivBattery != null) {
                    int battery = switchBattery(key);
                    reference.get().ivBattery.setImageLevel(battery);
                }
            }
        }

        // 电量强度转电量值
        private int switchBattery(final int battery) {
            int level = 0;

            if (battery <= 10) {
                level = 0;
            }
            else if (battery > 10 && battery <= 30) {
                level = 1;
            }
            else if (battery > 30 && battery <= 50) {
                level = 2;
            }
            else if (battery > 50) {
                level = 3;
            }
            return level;
        }
    }

}
