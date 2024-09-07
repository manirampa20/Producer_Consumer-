package performancecomparison;

import performancecomparison.PerformanceMetrics;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// Producer-Consumer with BlockingQueue and Metrics
class ProducerConsumerBlockingQueueWithMetrics {
    private final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5);
    private final PerformanceMetrics metrics = new PerformanceMetrics();

    public void produce() throws InterruptedException {
        int value = 0;
        metrics.start();
        while (true) {
            buffer.put(value); // Automatically blocks if the queue is full
            System.out.println("Produced: " + value);
            metrics.increment();
            value++;
            Thread.sleep(500); // Simulate time to produce
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            int value = buffer.take(); // Automatically blocks if the queue is empty
            System.out.println("Consumed: " + value);
            Thread.sleep(500); // Simulate time to consume
        }
    }

    public void runBlockingQueue() {
        Thread producerThread = new Thread(() -> {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
