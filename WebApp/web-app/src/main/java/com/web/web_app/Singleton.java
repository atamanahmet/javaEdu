package com.web.web_app;

public class Singleton {
    private static volatile Singleton instance;
    private String data;

    private Singleton(String data) {
        this.data = data;
    }

    public static Singleton getInstance(String data) {
        // long startTime = System.nanoTime();

        // if (instance == null) {

        // synchronized (Singleton.class) {

        // if (instance == null) {

        // instance = new Singleton(data);
        // }
        // }
        // }

        Singleton result = instance;

        if (result == null) {

            synchronized (Singleton.class) {

                if (result == null) {

                    instance = result = new Singleton(data);
                }
            }
        }
        // long stopTime = System.nanoTime();
        // System.out.println("Time in nanos " + (stopTime - startTime));

        return result;
    }

    public String getData() {
        return this.data;
    }

    // public void printData(int times) {
    // for (int i = 0; i < times; i++) {
    // System.out.println(this.data);
    // }
    // }
}
