package eu.mapow.java21;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.mapow.java.threads.ThreadSleep;

/**
 * https://openjdk.org/jeps/444
 *
 * Completable futures are another asynchronous alternative. Some of the main differences between completable futures
 * and virtual threads:
 *
 * <pre>
    - Style: CompletableFuture has a callback-based style while virtual threads allow a synchronous coding style for asynchronous logic.
    - Blocking: Blocking operations on virtual threads don’t consume OS threads like they would with completable futures.
    - Overhead: Virtual threads have lower resource overhead than threads/thread pools used by completable futures.
    - Structued Concurrency: Virtual threads inherit context from parents while completable futures don’t have a built-in concept of parent-child relationships.
    - Chaining: CompletableFuture makes it easier to directly chain multiple future stages together. Chaining logic with virtual threads requires explicit code.
    - Error Handling: Unhandled exceptions on virtual threads terminate the thread while completable futures require handling via callbacks.
 * </pre>
 *
 * @author mapow
 * @since 2024-12-03
 */
public class VirtualThreads {

    private static final Logger LOG = LoggerFactory.getLogger(VirtualThreads.class);
    private static final int THREAD_LIMIT = 2000;

    /**
     * Create 2000 threads from Runnable and execute them with virtual threads or platform threads to compare the
     * performance of both. We will use the Duration.between() API to measure the elapsed time in executing all the
     * tasks.<br>
     *
     * With the ThreadPool Executor, we are using a pool of 100 platform threads. In this way, Executor will be able to
     * run 100 tasks at a time and other tasks will need to wait. As we have 5000 tasks so the total time to finish the
     * execution will be approximately 20 seconds.<br>
     *
     * The new VirtualThread per task executor will be between 1 and 3 seconds fast - depending on the start sequence of
     * the tests. Starting 10.000 virtual threads will be also between 1 and 3 seconds fast. The ThreadPool Executor
     * will run approximately 100 seconds.
     *
     * @param virtualThreads true for newVirtualThreadPerTaskExecutor | false for newFixedThreadPool
     */
    public double runThreads(final boolean virtualThreads) {

        final Runnable runnable = createRunnable();
        final Instant start = Instant.now();
        try (var executor = createExecutor(virtualThreads)) {
            for (int i = 0; i < THREAD_LIMIT; i++) {
                executor.submit(runnable);
            }
        }
        final Instant finish = Instant.now();
        final double timeElapsed = Double.valueOf(Duration.between(start, finish).toMillis()) / 1000;
        LOG.debug("Total elapsed time : {} Seconds", timeElapsed);
        return timeElapsed;
    }

    /**
     * Simple task that waits for 1 second before printing a message. We are creating this task to keep the example
     * simple so we can focus on the concept.
     */
    private Runnable createRunnable() {
        final AtomicInteger atomicInteger = new AtomicInteger();
        return () -> {
            ThreadSleep.sleepOneSecond();
            if (atomicInteger.incrementAndGet() % 100 == 0) {
                LOG.debug("Work Done - {} of {}", atomicInteger.get(), THREAD_LIMIT);
            }
        };
    }

    private ExecutorService createExecutor(final boolean virtualThreads) {
        return virtualThreads ? Executors.newVirtualThreadPerTaskExecutor() : Executors.newFixedThreadPool(100);
    }
}
