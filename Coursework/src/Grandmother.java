import java.util.Random;
import java.util.concurrent.Semaphore;

public class Grandmother extends Thread {

	private ThreadGroup ThreadGroup;
	private CurrentAccount studentAccount = new CurrentAccount();
	private String grandmotherName = "Anna";
	private final Semaphore grandmotherSem;

	@Override
	public void run() {
		System.out.println("Start Grandmother Thread");

		Transaction christmasGift = new Transaction(grandmotherName, 100);
		studentAccount.deposit(christmasGift);
		System.out.println("Grandmother makes a deposit for Christmas gift");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Transaction birthdayGift = new Transaction(grandmotherName, 250);
		studentAccount.deposit(birthdayGift);
		System.out.println("Grandmother makes a deposit for Birthday gift");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Terminate Grandmother Thread");
	}

	public int getSleepTime() {
		Random rand = new Random();
		int sleepTime = rand.nextInt((10000 - 1000) + 1) + 1000;
		return sleepTime;
	}

	public String getGrandmotherName() {
		return grandmotherName;
	}

	Grandmother(String t, ThreadGroup tg, Semaphore sem) {
		super(tg, t);
		this.ThreadGroup = tg;
		this.grandmotherSem = sem;
	}

}
