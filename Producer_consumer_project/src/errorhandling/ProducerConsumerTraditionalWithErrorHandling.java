package errorhandling;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// Traditional Producer-Consumer with Error Handling
public class ProducerConsumerTraditionalWithErrorHandling {
    private final int MAX_SIZE = 5;
    private final Queue<Integer> buffer = new LinkedList<>();

    public void produce() {
        int value = 0;
        while (true) {
            synchronized (this) {
                try {
                    while (buffer.size() == MAX_SIZE) {
                        wait(); // Wait until there is space in the buffer
                    }
                    buffer.add(value);
                    System.out.println("Produced: " + value);
                    notify(); // Notify the consumer that there is something to consume
                    value++;
                    Thread.sleep(500); // Simulate time to produce
                } catch (InterruptedException e) {
                    System.out.println("Producer interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt(); // Re-interrupt the thread
                }
            }
        }
    }

    public void consume() {
        while (true) {
            synchronized (this) {
                try {
                    while (buffer.isEmpty()) {
                        wait(); // Wait until there is something to consume
                    }
                    int value = buffer.poll();
                    System.out.println("Consumed: " + value);
                    notify(); // Notify the producer that space is available
                    Thread.sleep(500); // Simulate time to consume
                } catch (InterruptedException e) {
                    System.out.println("Consumer interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt(); // Re-interrupt the thread
                }
            }
        }
    }

    public void runTraditional() {
        Thread producerThread = new Thread(this::produce);
        Thread consumerThread = new Thread(this::consume);

        producerThread.start();
        consumerThread.start();
    }
}


