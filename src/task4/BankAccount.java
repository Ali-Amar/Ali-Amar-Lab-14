package task4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class BankAccount {
    private AtomicInteger balance;
    private static final Random random = new Random();
    
    public BankAccount(int initialBalance) {
        this.balance = new AtomicInteger(initialBalance);
    }
    
    public void deposit(int amount) {
        balance.addAndGet(amount);
        System.out.println(Thread.currentThread().getName() + 
            " deposited " + amount + ". New balance: " + balance);
    }
    
    public void withdraw(int amount) {
        if (balance.get() >= amount) {
            balance.addAndGet(-amount);
            System.out.println(Thread.currentThread().getName() + 
                " withdrew " + amount + ". New balance: " + balance);
        }
    }
    
    public int getBalance() {
        return balance.get();
    }
    
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(1000);
        
        // Create multiple client threads
        Thread[] clients = new Thread[5];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    if (random.nextBoolean()) {
                        account.deposit(random.nextInt(100));
                    } else {
                        account.withdraw(random.nextInt(100));
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Client-" + (i + 1));
            clients[i].start();
        }
        
        // Wait for all clients to complete
        for (Thread client : clients) {
            client.join();
        }
        
        System.out.println("Final balance: " + account.getBalance());
    }
}