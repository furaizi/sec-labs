package org.example.lab3;

import java.util.List;
import java.util.concurrent.CancellationException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var token = new CancellationToken();

        AsyncStreamUtils.asyncMap(
                List.of(1, 2, 3, 4, 5),
                x -> x * 2,
                result -> System.out.println("Success: " + result),
                error -> {
                    if (error instanceof CancellationException) {
                        System.out.println("Task was cancelled.");
                    } else {
                        System.err.println("Error: " + error.getMessage());
                    }
                },
                token
        );
        
        Thread.sleep(100);
        token.cancel();

        AsyncStreamUtils.shutdown();
    }
}
