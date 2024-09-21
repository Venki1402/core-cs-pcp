import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {
    private Queue<Object> store;
    private int maxSize;
    private String name;
    private Semaphore producerSemaphore;
    private Semaphore consumerSemaphore;

    public Consumer(Queue<Object> store, int maxSize, String name, Semaphore producerSemaphore,
            Semaphore consumerSemaphore) {
        this.store = store;
        this.maxSize = maxSize;
        this.name = name;
        this.producerSemaphore = producerSemaphore;
        this.consumerSemaphore = consumerSemaphore;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consumerSemaphore.acquire(); // Wait if the store is empty
                synchronized (store) {
                    if (store.size() > 0) {
                        System.out.println(name + " consumed an object. Current size: " + (store.size() - 1));
                        store.remove();
                    }
                }
                producerSemaphore.release(); // Signal that an item has been consumed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}