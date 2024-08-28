package com.bondar.study.threads.deadlock.problem;

import java.util.Random;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();

        Thread thread1 = new Thread(() -> runner.firstThread());

        Thread thread2 = new Thread(() -> runner.secondThread());

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        runner.finished();

    }

}

class Runner {
    private Account account1 = new Account();
    private Account account2 = new Account();

    private Random random = new Random();

    public void firstThread() {
        for (int i = 0; i < 10000; i++) {
            synchronized (account1) {
                synchronized (account2) {
                    Account.transfer(account1, account2, random.nextInt(100));
                }
            }
        }
    }

    public void secondThread() {
        for (int i = 0; i < 10000; i++) {
            synchronized (account2) {
                synchronized (account1) {
                    Account.transfer(account2, account1, random.nextInt(100));
                }
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