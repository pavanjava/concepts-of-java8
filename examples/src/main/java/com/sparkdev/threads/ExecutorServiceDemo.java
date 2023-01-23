package com.sparkdev.threads;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Main methods to learn in executor service are as below
 * execute()
 * shutDown()
 * awaitTermination()
 * <p>
 * submit(new Runnable())
 * submit(new Callable())
 * invokeAny()
 * invokeAll()
 */

class Service implements Runnable {

    int i;

    Service(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("i : " + i);
        try {
            Thread.sleep(1000); // wait for 1 sec
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class ExecutorServiceDemo {

    public static void main(String[] args) throws InterruptedException {
        try (ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            System.out.println(Instant.now());
            for (int i = 0; i < 25; i++) {
                executorService.execute(new Service(i));
            }
            // executorService.shutdown(); // will not wait for all child threads to finish
            executorService.awaitTermination(10, TimeUnit.SECONDS); // will wait for all child threads to finish
        }
        System.out.println(Instant.now());
    }

}
