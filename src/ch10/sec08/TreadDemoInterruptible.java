package ch10.sec08;

public class TreadDemoInterruptible {

    public static void main(String[] args) throws InterruptedException {
    	
    	Runnable interruptibleTask = () -> {
            System.out.print("\nInterruptible: ");
            
            try {
                for (int i = 1; i <= 100; i++) {
                    System.out.print(i + " ");
                    Thread.sleep(300);
                }
            }
            catch (InterruptedException ex) {
                System.out.println("Interrupted!");
            }
        };
        
       Thread thread = new Thread(interruptibleTask);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();         

	}

}
