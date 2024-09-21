import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Queue<Object> store = new ArrayDeque<>();
        int maxSize = 6;

        Semaphore producerSemaphore = new Semaphore(maxSize);
        Semaphore consumerSemaphore = new Semaphore(0);

        Producer p1 = new Producer(store, maxSize, "Producer 1", producerSemaphore, consumerSemaphore);
        Producer p2 = new Producer(store, maxSize, "Producer 2", producerSemaphore, consumerSemaphore);
        Consumer c1 = new Consumer(store, maxSize, "Consumer 1", producerSemaphore, consumerSemaphore);
        Consumer c2 = new Consumer(store, maxSize, "Consumer 2", producerSemaphore, consumerSemaphore);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(c1);
        Thread t4 = new Thread(c2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}