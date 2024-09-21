class NumberPrinter implements Runnable {
    int num;

    public NumberPrinter(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("Number: " + num + " printed by " + Thread.currentThread().getName());
    }
}