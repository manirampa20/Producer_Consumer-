package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

class ProducerConsumerTraditional {

    private final int MAX_SIZE = 5;
    private final Queue<Integer> buffer = new LinkedList<>();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (buffer.size() == MAX_SIZE) {
                    wait(); // Wait until there is space in the buffer
                }
                System.out.println("Produced: " + value);
                buffer.add(value++);
                notify(); // Notify the consumer that there is something to consume
                Thread.sleep(1000); // Simulate time to produce
            }
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
                Thread.sleep(1000); // Simulate time to consume
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerTraditional pc = new ProducerConsumerTraditional();

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
