package blockingqueue;//package blockingqueue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ProducerConsumerBlockingQueue {

    private final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5);
    private volatile boolean running = true; // Flag to control the loop

    public void produce() throws InterruptedException {
        int value = 0;
        while (running) {
            System.out.println("Produced: " + value);
            buffer.put(value++); // Automatically blocks if the queue is full
            Thread.sleep(1000); // Simulate time to produce
        }
    }

    public void consume() throws InterruptedException {
        while (running) {
            int value = buffer.take(); // Automatically blocks if the queue is empty
            System.out.println("Consumed: " + value);
            Thread.sleep(1000); // Simulate time to consume
        }
    }

    public void stop() {
        running = false; // Set flag to false to stop production and consumption
    }

    public static void main(String[] args) {
        ProducerConsumerBlockingQueue pc = new ProducerConsumerBlockingQueue();

        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();

        // Example stop logic (e.g., stop after 10 seconds)
        try {
            Thread.sleep(10000); // Run for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pc.stop(); // Stop the producer and consumer
    }
}

