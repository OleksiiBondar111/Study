package com.bondar.study.threads.raceCondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    public static  int counter;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);


        executorService.submit(() -> {
           for (int i = 0; i < 10000; i++) {
               increment();
           }
        });

        executorService.submit(() -> {
            for (int i = 0; i < 10000; i++) {
               increment();
            }
        });

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Finished counter: " + counter);

    }

    public static synchronized void increment(){
        counter++;
    }

}
