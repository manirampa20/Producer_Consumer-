package performancecomparison;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// Performance Metrics Class to Measure Throughput and Latency
class PerformanceMetrics {
    private long startTime;
    private int itemCount = 0;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void increment() {
        itemCount++;
    }

    public void printMetrics() {
        long endTime = System.currentTimeMillis();
        double throughput = (double) itemCount / ((endTime - startTime) / 1000.0);
        System.out.println("Items processed: " + itemCount);
        System.out.println("Throughput (items/second): " + throughput);
        System.out.println("Latency (ms per item): " + (double) (endTime - startTime) / itemCount);
    }
}

// Traditional Producer-Consumer with wait/notify
class ProducerConsumerTraditionalWithMetrics {
    private final int MAX_SIZE = 5;
    private final Queue<Integer> buffer = new LinkedList<>();
    private final PerformanceMetrics metrics = new PerformanceMetrics();

    public void produce() throws InterruptedException {
        int value = 0;
        metrics.start();
        while (true) {
            synchronized (this) {
                while (buffer.size() == MAX_SIZE) {
                    wait(); // Wait until there is space in the buffer
                }
                buffer.add(value);
                System.out.println("Produced: " + value);
                metrics.increment();
                notify(); // Notify the consumer that there is something to consume
                value++;
            }
            Thread.sleep(500); // Simulate time to produce
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    wait(); // Wait until there is something to consume
                }
                int value = buffer.poll();
                System.out.println("Consumed: " + value);
                notify(); // Notify the producer that space is available
            }
            Thread.sleep(500); // Simulate time to consume
        }
    }

    public void runTraditional() {
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


