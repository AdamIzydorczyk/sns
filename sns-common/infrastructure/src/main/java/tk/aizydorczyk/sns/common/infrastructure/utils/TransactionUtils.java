package tk.aizydorczyk.sns.common.infrastructure.utils;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

public class TransactionUtils {

    @Transactional(propagation = Propagation.REQUIRED)
    public <ResultType> ResultType runInTransaction(Supplier<ResultType> supplier) {
        return supplier.get();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void runInTransaction(Runnable runnable) {
        runnable.run();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <ResultType> ResultType runInNewTransaction(Supplier<ResultType> supplier) {
        return supplier.get();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void runInNewTransaction(Runnable runnable) {
        runnable.run();
    }
}
