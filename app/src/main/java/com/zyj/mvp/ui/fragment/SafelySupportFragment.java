package com.zyj.mvp.ui.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.zyj.mvp.ui.activity.StartActivityDelegate;

/**
 * Created by 张垚杰 on 2018/1/26.
 */

public class SafelySupportFragment extends Fragment implements TransactionCommitter{

    private final SupportFragmentTransactionDelegate mSupportFragmentTransactionDelegate =
            new SupportFragmentTransactionDelegate();

    protected boolean startActivitySafely(final Intent intent){
        return StartActivityDelegate.startActivitySafely(this,intent);
    }

    protected boolean safeCommit(@NonNull FragmentTransaction transaction){
        return mSupportFragmentTransactionDelegate.safeCommit(this,transaction);
    }

    @Override
    public void onResume() {
        super.onResume();
        mSupportFragmentTransactionDelegate.onResumed();
    }

    @Override
    public boolean isCommitterResumed() {
        return isResumed();
    }
}
