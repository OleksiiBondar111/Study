package com.bondar.study.threads.semaphore;


// We use Semaphore when we want to limit access to some resource.

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Connection {

    private static final Connection connection = new Connection();
    private int connectionsCount;
    private Semaphore semaphore = new Semaphore(10);

    private Connection() {

    }

    public static Connection getConnection() {
        return connection;
    }

    public void work() throws InterruptedException {
        semaphore.acquire();
        try {
            doWork();
        } finally {
            semaphore.release();
        }
    }

    private void doWork() throws InterruptedException {
        synchronized (this) {
            connectionsCount++;
            System.out.println(connectionsCount);
        }

        Thread.sleep(5000);

        synchronized (this) {
            connectionsCount--;
        }

    }

}


public class Test {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Connection connection = Connection.getConnection();
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    connection.work();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
