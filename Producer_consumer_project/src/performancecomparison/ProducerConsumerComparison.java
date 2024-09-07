package performancecomparison;

public class ProducerConsumerComparison {
    public static void main(String[] args) {
        System.out.println("Starting Traditional Producer-Consumer with Metrics:");
        ProducerConsumerTraditionalWithMetrics traditionalPC = new ProducerConsumerTraditionalWithMetrics();
        traditionalPC.runTraditional();

        try {
            // Let the traditional approach run for some time before switching
            Thread.sleep(15000); // Run for 15 seconds to measure performance
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("\nStarting BlockingQueue Producer-Consumer with Metrics:");
        ProducerConsumerBlockingQueueWithMetrics blockingQueuePC = new ProducerConsumerBlockingQueueWithMetrics();
        blockingQueuePC.runBlockingQueue();

        // Let the BlockingQueue approach run for the same amount of time
        try {
            Thread.sleep(15000); // Run for 15 seconds to measure performance
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        // Compare results
        System.out.println("\nPerformance comparison complete.");
    }
}
