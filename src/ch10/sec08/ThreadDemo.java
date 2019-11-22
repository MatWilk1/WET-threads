package ch10.sec08;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> { 
        	for (int i = 1; i <= 100; i++) System.out.print(i + " "); 
        	};
        Thread thread = new Thread(task); 
        thread.start();
        System.out.println("Started thread.");
        thread.join();
        System.out.println("\nJoined thread.");
     
    }    
}
