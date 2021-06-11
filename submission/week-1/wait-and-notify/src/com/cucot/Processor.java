package com.cucot;

public class Processor {

    private float score = 1.5f;

    public void consume() throws InterruptedException {
        synchronized (this) {
            // wait and check
            while (this.score > 0.5) {
                if (this.score > 0.5) {
                    System.out.println("Now I have to wait because the value is: " + this.score);
                    wait();
                }
            }
            System.out.println("Finally got a random number no more than 0.5");
        }
    }

    public void produce() throws InterruptedException {
        System.out.println("attemping to get lock");
        synchronized (this) {
            System.out.println("inside produce sync");
            while (this.score > 0.5f) {
                this.score = (float) Math.random();
                System.out.println("generating a number other than " + this.score);
                Thread.sleep(1000);
            }
            notify();
        }
    }
}
