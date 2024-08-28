package com.bondar.study.threads.deadlock.solution;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(() -> runner.firstThread());
        executorService.submit(() -> runner.secondThread());
        executorService.shutdown();
        runner.finished();

    }

}

class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private Random random = new Random();

    private void takeLocks(Lock lock1, Lock lock2) {
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;

        while (true) {
            try {
                firstLockTaken = lock1.tryLock();
                secondLockTaken = lock2.tryLock();
            } finally {
                if (firstLockTaken && secondLockTaken) {
                    return;
                }
                if (firstLockTaken) {
                    lock1.unlock();
                }
                if (secondLockTaken) {
                    lock2.unlock();
                }
            }
        }
    }

    public void firstThread() {
        for (int i = 0; i < 10000; i++) {
            takeLocks(lock1, lock2);
            try {
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() {
        for (int i = 0; i < 10000; i++) {
            takeLocks(lock1, lock2);
            try {
                Account.transfer(account2, account1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() {
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
        System.out.println("Total amount: " + (account1.getBalance() + account2.getBalance()));
    }
}

class Account {
    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public static void transfer(Account account1, Account account2, int amount) {
        account1.withdraw(amount);
        account2.deposit(amount);
    }

    public int getBalance() {
        return balance;
    }
}