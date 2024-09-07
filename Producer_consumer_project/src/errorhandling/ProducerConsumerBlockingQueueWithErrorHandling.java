package errorhandling;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// Producer-Consumer with BlockingQueue and Error Handling
public class ProducerConsumerBlockingQueueWithErrorHandling {
    private final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5);

    public void produce() {
        int value = 0;
        while (true) {
            try {
                buffer.put(value); // Automatically blocks if the queue is full
                System.out.println("Produced: " + value);
                value++;
                Thread.sleep(500); // Simulate time to produce
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted: " + e.getMessage());
                Thread.currentThread().interrupt(); // Re-interrupt the thread
            }
        }
    }

    public void consume() {
        while (true) {
            try {
                int value = buffer.take(); // Automatically blocks if the queue is empty
                System.out.println("Consumed: " + value);
                Thread.sleep(500); // Simulate time to consume
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted: " + e.getMessage());
                Thread.currentThread().interrupt(); // Re-interrupt the thread
            }
        }
    }

    public void runBlockingQueue() {
        Thread producerThread = new Thread(this::produce);
        Thread consumerThread = new Thread(this::consume);

        producerThread.start();
        consumerThread.start();
    }
}
