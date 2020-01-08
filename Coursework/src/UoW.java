import java.util.Random;
import java.util.concurrent.Semaphore;

public class UoW extends Thread {

	public ThreadGroup ThreadGroup;
	private CurrentAccount studentAccount = new CurrentAccount();
	private String organisationName = "UOW";
	private final Semaphore uniSem;

	@Override
	public void run() {
		System.out.println("Start University Thread");

		Transaction courseFee1 = new Transaction(organisationName, 10000);
		studentAccount.withdrawal(courseFee1);
		System.out.println("University makes a withdrawal for first installment of Course Fee");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Transaction courseFee2 = new Transaction(organisationName, 10000);
		studentAccount.withdrawal(courseFee2);
		System.out.println("University makes a withdrawal for second installment of Course Fee");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Transaction courseFee3 = new Transaction(organisationName, 10000);
		studentAccount.withdrawal(courseFee3);
		System.out.println("University makes a withdrawal for third installment of Course Fee");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Terminate University Thread");
	}

	public int getSleepTime() {
		Random rand = new Random();
		int sleepTime = rand.nextInt((10000 - 1000) + 1) + 1000;
		return sleepTime;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	UoW(String t, ThreadGroup tg, Semaphore sem) {
		super(tg, t);
		this.ThreadGroup = tg;
		this.uniSem = sem;
	}

}
