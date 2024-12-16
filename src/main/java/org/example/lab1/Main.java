package org.example.lab1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        var numbers = Arrays.asList(1, 2, 3, 4, 5);
        AsyncStreamUtils.asyncMap(
                numbers,
                n -> n * 2,
                list -> System.out.println("Result: " + list),
                e -> System.err.println("Error: " + e.getMessage())
        );

        AsyncStreamUtils.shutdown();
    }
}
