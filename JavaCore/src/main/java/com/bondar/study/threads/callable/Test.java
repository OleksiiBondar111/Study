package com.bondar.study.threads.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(() -> {
            System.out.println("Test callable");
            return "Called test callable";
        });
        executorService.shutdown();
        System.out.println("Result: " + future.get());
    }
}
