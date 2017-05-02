package com.mralonso.android.domain.execution;

import com.mralonso.android.domain.useCases.AbstractUseCase;

import java.util.concurrent.Future;

/**
 * This executor is responsible for running useCases on background threads.
 */
public interface Executor {

    /**
     * This method should call the useCase's run method and thus start the useCase. This should be called
     * on a background thread as useCases might do lengthy operations.
     *
     * @param useCase The useCase to run.
     */
    Future execute(final AbstractUseCase useCase);
}
