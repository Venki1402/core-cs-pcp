import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main{
    public static void main(String[] args) {

        // using exectuor service
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i = 0; i < 10; i++) {
            executorService.execute(new NumberPrinter(i));
        }

        executorService.shutdown();

        // for(int i = 0; i < 10; i++) {
        //     Thread thread = new Thread(new NumberPrinter(i));
        //     thread.start();
        // }
    }
}