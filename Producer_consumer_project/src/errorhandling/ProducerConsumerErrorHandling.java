package errorhandling;



public class ProducerConsumerErrorHandling {
    public static void main(String[] args) {
        System.out.println("Starting Traditional Producer-Consumer with Error Handling:");
        ProducerConsumerTraditionalWithErrorHandling traditionalPC = new ProducerConsumerTraditionalWithErrorHandling();
        traditionalPC.runTraditional();

        System.out.println("\nStarting BlockingQueue Producer-Consumer with Error Handling:");
        ProducerConsumerBlockingQueueWithErrorHandling blockingQueuePC = new ProducerConsumerBlockingQueueWithErrorHandling();
        blockingQueuePC.runBlockingQueue();
    }
}