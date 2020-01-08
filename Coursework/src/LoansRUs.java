import java.util.Random;
import java.util.concurrent.Semaphore;

public class LoansRUs extends Thread {

	private ThreadGroup ThreadGroup;
	private CurrentAccount studentAccount = new CurrentAccount();
	private String organisationName = "LoansRUs";
	private final Semaphore loanSem;

	@Override
	public void run() {
		System.out.println("Start Loan Thread");

		Transaction loan1 = new Transaction(organisationName, 10000);
		studentAccount.deposit(loan1);
		System.out.println("Loan company makes a deposit for first installment of Student loan");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Transaction loan2 = new Transaction(organisationName, 10000);
		studentAccount.deposit(loan2);
		System.out.println("Loan company makes a deposit for second installment of Student loan");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Transaction loan3 = new Transaction(organisationName, 10000);
		studentAccount.deposit(loan3);
		System.out.println("Loan company makes a deposit for third installment of Student loan");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Terminate Loan Thread");
	}

	public int getSleepTime() {
		Random rand = new Random();
		int sleepTime = rand.nextInt((10000 - 1000) + 1) + 1000;
		return sleepTime;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	LoansRUs(String t, ThreadGroup tg, Semaphore sem) {
		super(tg, t);
		this.ThreadGroup = tg;
		this.loanSem = sem;
	}

}
