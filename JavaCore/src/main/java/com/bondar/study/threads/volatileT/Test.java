package com.bondar.study.threads.volatileT;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    public static volatile int counter;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);


        executorService.submit(() -> {
            int local_counter = counter;
            while (local_counter < 10) {
                if (local_counter != counter) {
                    System.out.println("[T1] counter: " + counter);
                    local_counter = counter;
                }
            }
        });

        executorService.submit(() -> {
            int local_counter = counter;
            while (local_counter < 10) {
                counter = ++local_counter;
                System.out.println("[T2] counter: " + (local_counter));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Finished");

    }

}
