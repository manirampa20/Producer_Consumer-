package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ProducerConsumerBlockingQueue {

    private final BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5);

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            System.out.println("Produced: " + value);
            buffer.put(value++); // Automatically blocks if the queue is full
            Thread.sleep(1000); // Simulate time to produce
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            int value = buffer.take(); // Automatically blocks if the queue is empty
            System.out.println("Consumed: " + value);
            Thread.sleep(1000); // Simulate time to consume
        }
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
    }
}
