package com.sparkdev.threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureDemo {

    static int data = 5;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            data = 10;
        });

        System.out.println("data: "+data);
        Thread.sleep(3000);
        System.out.println("data: "+data);

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "data from supply async";
        });
        System.out.println(stringCompletableFuture.isDone());
        System.out.println(stringCompletableFuture.thenApply(String::toUpperCase).get());
        System.out.println(stringCompletableFuture.isDone());
    }
}
