package ReactorIO.EventLoop;

import ReactorIO.Config;

import java.util.concurrent.*;

/**
 * Created by faraonul on 5/25/17.
 */
public class EventLoop {
    private static EventLoop instance = null;
    private ExecutorService threadPool;
    private EventLoop() {
         threadPool = Executors.newFixedThreadPool(Config.NUMBER_OF_THREADS);
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


    public static CompletableFuture makeCompletable(Future future) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return future.get();
            } catch (InterruptedException|ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
