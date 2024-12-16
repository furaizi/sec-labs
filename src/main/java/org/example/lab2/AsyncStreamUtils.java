package org.example.lab2;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class AsyncStreamUtils {

    public static <T, R> CompletableFuture<List<R>> asyncMap(
            List<T> input,
            Function<T, R> mapper
    ) {
        return CompletableFuture.supplyAsync(() -> input.stream().map(mapper).toList());
    }

}
