package Reactor.EventLoop;

import java.util.concurrent.*;

/**
 * Created by faraonul on 5/25/17.
 */
public class EventLoop {
    private static EventLoop instance = null;
    private ExecutorService threadPool;
    private EventLoop() {
         threadPool = Executors.newFixedThreadPool(10);
    }

    public CompletableFuture<Void> runAsync(Runnable runnable) {
        return CompletableFuture.runAsync(runnable, threadPool);
    }

    public static EventLoop getInstance() {
        if(instance == null) {
            instance = new EventLoop();
        }
        return instance;
    }


    public static <T> CompletableFuture<T> makeCompletable(Future<T> future) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return future.get();
            } catch (InterruptedException|ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
