package org.example.lab3;

import java.util.concurrent.CompletableFuture;

public class CancellationToken {
    public static final String CANCEL_MESSAGE = "Task was canceled.";
    private final CompletableFuture<Void> cancelled = new CompletableFuture<>();

    public boolean isCancelled() {
        return cancelled.isDone();
    }

    public void cancel() {
        cancelled.complete(null);
    }

    public CompletableFuture<Void> getCancelledFuture() {
        return cancelled;
    }
}
