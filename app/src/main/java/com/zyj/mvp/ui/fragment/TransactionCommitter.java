package com.zyj.mvp.ui.fragment;

/**
 * Created by 张垚杰 on 2018/1/26.
 * {@link android.support.v4.app.FragmentTransaction} from, we should commit only when it's resumed,
 * avoiding the Activity state loss error.
 */

public interface TransactionCommitter {
    /**
     * whether the host is resumed
     *
     * @return {@code true} if it's resumed.
     */
    boolean isCommitterResumed();
}
