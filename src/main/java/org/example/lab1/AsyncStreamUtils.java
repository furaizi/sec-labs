package org.example.lab1;

import java.util.List;
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
            Consumer<Exception> onError
    ) {
        executor.submit(() -> {
            try {
                var result = input.stream().map(mapper).toList();
                onSuccess.accept(result);
            }
            catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    public static void shutdown() {
        executor.shutdown();
    }
}
