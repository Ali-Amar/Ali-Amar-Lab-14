package task1;

public class NumberPrinter {
    public static void main(String[] args) {
        // Create first thread using Thread class
        Thread numberThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Number: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create second thread using Thread class
        Thread squareThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Square: " + (i * i));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        numberThread.start();
        squareThread.start();
    }
}