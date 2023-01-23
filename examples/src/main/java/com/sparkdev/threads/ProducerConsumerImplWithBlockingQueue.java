package com.sparkdev.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Consumer implements Runnable {

    BlockingQueue<Integer> blockingQueue;

    public Consumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
        Thread consumer = new Thread(this);
        consumer.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("consumer : " + blockingQueue.take()); // consume the data
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

// producer class that produces some data
class Producer implements Runnable {

    BlockingQueue<Integer> blockingQueue;

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
        Thread producer = new Thread(this);
        producer.start();
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            System.out.println("producer : " + i);
            try {
                Thread.sleep(1000);
                blockingQueue.put(i++); // produce the data
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class ProducerConsumerImplWithBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(2);
        new Producer(blockingQueue);
        new Consumer(blockingQueue);
    }
}
