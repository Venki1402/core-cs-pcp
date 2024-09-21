import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Producer implements Runnable {
    private Queue<Object> store;
    private int maxSize;
    private String name;
    private Semaphore producerSemaphore;
    private Semaphore consumerSemaphore;

    public Producer(Queue<Object> store, int maxSize, String name, Semaphore producerSemaphore,
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
                producerSemaphore.acquire(); // Wait if the store is full
                synchronized (store) {
                    if (store.size() < maxSize) {
                        store.add(new Object());
                        System.out.println(name + " produced an object. Current size: " + store.size());
                    }
                }
                consumerSemaphore.release(); // Signal that an item has been produced
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}