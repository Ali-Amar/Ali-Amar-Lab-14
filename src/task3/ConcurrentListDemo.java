package task3;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;

public class ConcurrentListDemo {
    private static final CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
    private static final Random random = new Random();
    
    public static void main(String[] args) throws InterruptedException {
        // Create writer threads
        Thread[] writers = new Thread[3];
        for (int i = 0; i < writers.length; i++) {
            writers[i] = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    int number = random.nextInt(100);
                    list.add(number);
                    System.out.println("Added: " + number);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        
        // Create reader threads
        Thread[] readers = new Thread[2];
        for (int i = 0; i < readers.length; i++) {
            readers[i] = new Thread(() -> {
                for (int j = 0; j < 3; j++) {
                    System.out.println("Current list: " + list);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        
        // Start all threads
        for (Thread writer : writers) writer.start();
        for (Thread reader : readers) reader.start();
        
        // Wait for all threads to complete
        for (Thread writer : writers) writer.join();
        for (Thread reader : readers) reader.join();
    }
}