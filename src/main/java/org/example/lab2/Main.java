package org.example.lab2;

import java.time.Duration;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        var numbers = Arrays.asList(1, 2, 3, 4, 5);

        AsyncStreamUtils.asyncMap(numbers, n -> n * 2)
                .thenAccept(result -> System.out.println("Result: " + result))
                .exceptionally(e -> {
                    System.err.println("Error: " + e.getMessage());
                    return null;
                });

//        Thread.sleep(Duration.ofSeconds(5));
    }
}
