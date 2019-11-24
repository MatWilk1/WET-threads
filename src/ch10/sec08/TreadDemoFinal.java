package ch10.sec08;

import java.util.Random;

public class TreadDemoFinal {

	public static void main(String[] args) throws InterruptedException {

		Runnable interruptibleTask = () -> {
			System.out.print("\nInterruptible: ");

			Random random = new Random();
			int x = random.nextInt();
			System.out.println(x);

			if (x % 2 == 0) {
				System.out.print("\nExceptional: ");
				for (int i = 1; i <= 100; i++) {
					System.out.print(i + " ");
					if (i == 32)
						throw new IllegalStateException();
				}
			} else {

				try {
					for (int i = 1; i <= 100; i++) {
						System.out.print(i + " ");
						Thread.sleep(300);
					}
				} catch (InterruptedException ex) {
					System.out.println("First interraption");
				}

			}

		};

		Thread thread = new Thread(interruptibleTask);

		thread.setUncaughtExceptionHandler((t, ex) -> System.out.println("Second interraption"));
		thread.start();
		Thread.sleep(1000);
		thread.interrupt();

	}

}
