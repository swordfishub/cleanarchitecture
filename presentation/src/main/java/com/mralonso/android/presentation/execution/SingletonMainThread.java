package com.mralonso.android.presentation.execution;

import android.os.Handler;
import android.os.Looper;

import com.mralonso.android.domain.execution.MainThread;

/**
 * This class makes sure that the runnable we provide will be run on the main UI thread.
 */
public class SingletonMainThread implements MainThread {

    private static MainThread sMainThread;

    private Handler mHandler;

    private SingletonMainThread() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new SingletonMainThread();
        }

        return sMainThread;
    }
}
