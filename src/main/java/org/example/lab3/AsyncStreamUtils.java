package org.example.lab3;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;

public class AsyncStreamUtils {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static <T, R> void asyncMap(
            List<T> input,
            Function<T, R> mapper,
            Consumer<List<R>> onSuccess,
            Consumer<Exception> onError,
            CancellationToken token
    ) {
        executor.submit(() -> {
            try {
                var result = input.stream().map(element -> {
                    try {
                        Thread.sleep(Duration.ofMillis(40));
                        if (token.isCancelled())
                            throw new CancellationException(CancellationToken.CANCEL_MESSAGE);
                    }
                    catch (InterruptedException e) {
                        onError.accept(e);
                    }
                    return mapper.apply(element);
                }).toList();

                onSuccess.accept(result);
            }
            catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    public static <T, R> CompletableFuture<List<R>> asyncMap(
            List<T> input,
            Function<T, R> mapper,
            CancellationToken token
    ) {
        return CompletableFuture.supplyAsync(() -> input.stream().map(element -> {
                    if (token.isCancelled())
                        throw new CancellationException(CancellationToken.CANCEL_MESSAGE);
                    return mapper.apply(element);
                }).toList());
    }

    public static void shutdown() {
        executor.shutdown();
    }

}
