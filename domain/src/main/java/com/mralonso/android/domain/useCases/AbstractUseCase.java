package com.mralonso.android.domain.useCases;

import com.mralonso.android.domain.execution.Executor;
import com.mralonso.android.domain.execution.MainThread;

import java.util.concurrent.Future;

/**
 * This abstract class implements some common methods for all useCases. Cancelling an useCase, check if its running
 * and finishing an useCase has mostly the same code throughout so that is why this class was created. Field methods
 * are declared volatile as we might use these methods from different threads (mainly from UI).
 *
 * For example, when an activity is getting destroyed then we should probably cancel an useCase
 * but the request will come from the UI thread unless the request was specifically assigned to a background thread.
 */
public abstract class AbstractUseCase {

    protected Executor mThreadExecutor;
    protected MainThread mMainThread;

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    protected Future mRunningFuture;

    public AbstractUseCase(Executor threadExecutor, MainThread mainThread) {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
    }

    /**
     * This method contains the actual business logic of the useCase. It SHOULD NOT BE USED DIRECTLY but, instead, a
     * developer should call the execute() method of an useCase to make sure the operation is done on a background thread.
     *
     * This method should only be called directly while doing unit/integration tests. That is the only reason it is declared
     * public as to help with easier testing.
     */
    public abstract void run();

    public void cancel() {
        if(mRunningFuture!=null){
            mIsCanceled = mRunningFuture.cancel(true);
            mIsRunning = false;
        }
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public void execute() {

        // mark this useCase as running
        mIsRunning = true;

        // start running this useCase in a background thread
        mRunningFuture = mThreadExecutor.execute(this);
    }
}
