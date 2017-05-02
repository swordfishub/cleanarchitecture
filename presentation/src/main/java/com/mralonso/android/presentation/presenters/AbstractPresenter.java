package com.mralonso.android.presentation.presenters;

import com.mralonso.android.domain.execution.Executor;
import com.mralonso.android.domain.execution.MainThread;

public abstract class AbstractPresenter {

    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}
