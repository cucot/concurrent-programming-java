package com.cucot;

public class App implements Runnable {
    private final boolean produce;
    private final Processor processor;

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
        App consumeApp = new App(processor, false);
        App produceApp = new App(processor, true);
        System.out.println("after creating app");
        Thread thread = new Thread(consumeApp);
        Thread otherThread = new Thread(produceApp);
        thread.start();
        otherThread.start();
        thread.join();
        otherThread.join();
    }

    public App(Processor processor, boolean produce) {
        this.processor = processor;
        this.produce = produce;
    }


    @Override
    public void run() {
        try {
            if (this.produce) {
                this.processor.produce();
            } else {
                this.processor.consume();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
