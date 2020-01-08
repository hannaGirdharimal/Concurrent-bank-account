import java.util.Random;
import java.util.concurrent.Semaphore;

public class Student extends Thread {

	private ThreadGroup ThreadGroup;
	private CurrentAccount studentAccount = new CurrentAccount();
	private String studentName = "Jake";
	private final Semaphore studentSem;

	@Override
	public void run() {
		System.out.println("Start Student Thread");

		Transaction buyPhone = new Transaction(studentName, 1000);
		studentAccount.withdrawal(buyPhone);
		System.out.println("Student makes a withdrawal to buy phone");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Transaction winRaffle = new Transaction(studentName, 800);
		studentAccount.deposit(winRaffle);
		System.out.println("Student makes a deposit for Raffle money won");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Transaction buyTextbooks = new Transaction(studentName, 50);
		studentAccount.withdrawal(buyTextbooks);
		System.out.println("Student makes a withdrawal to buy textbooks");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Transaction donateToCharity = new Transaction(studentName, 25);
		studentAccount.withdrawal(donateToCharity);
		System.out.println("Student makes a withdrawal to donate cash");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Transaction buySkateboard = new Transaction(studentName, 100);
		studentAccount.withdrawal(buySkateboard);
		System.out.println("Student makes a withdrawal to buy skateboard");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Transaction wonCompetition = new Transaction(studentName, 200);
		studentAccount.withdrawal(wonCompetition);
		System.out.println("Student makes a deposit for money won at Toastmasters competition");
		try {
			Thread.sleep(getSleepTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		studentAccount.printStatement();
		System.out.println("Terminate Student Thread");
	}

	public int getSleepTime() {
		Random rand = new Random();
		int sleepTime = rand.nextInt((10000 - 1000) + 1) + 1000;
		return sleepTime;
	}

	public String getStudentName() {
		return studentName;
	}

	Student(String t, ThreadGroup tg, Semaphore sem) {
		super(tg, t);
		this.ThreadGroup = tg;
		this.studentSem = sem;
	}

}
