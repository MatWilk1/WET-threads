package ch10.sec08;

public class ThreadDemoExceptional {
	public static void main(String[] args) throws InterruptedException {
		
		Runnable exceptionalTask = () -> {
            System.out.print("\nExceptional: ");
            for (int i = 1; i <= 100; i++) {
                System.out.print(i + " ");
                if (i == 32) throw new IllegalStateException();
            }            
        };
        Thread thread = new Thread(exceptionalTask);
        thread.setUncaughtExceptionHandler((t, ex) -> System.out.println("Yikes!"));
        thread.start();
        thread.join();
		
	}

}
