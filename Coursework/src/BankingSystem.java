import java.util.concurrent.Semaphore;

public class BankingSystem {

	public static void main(String[] args) {
		CurrentAccount studentAccount = new CurrentAccount();
		ThreadGroup Human = new ThreadGroup("Human");
		ThreadGroup Organisation = new ThreadGroup("Organisation");
		Semaphore sem = new Semaphore(1,true);
		System.out.println("Initial balance : "+studentAccount.getBalance());
		
		Student student = new Student("studentThread", Human, sem);
		System.out.println("Student object created");
		Thread s = new Thread(student.getThreadGroup(), student);
		System.out.println("Student thread created");
		s.start();
		
		Grandmother grandmother = new Grandmother("grandmotherThread", Human, sem);
		System.out.println("Grandmother object created");
		Thread g = new Thread(grandmother.getThreadGroup(), grandmother);
		System.out.println("Grandmother thread created");
		g.start();

		LoansRUs loanCompany = new LoansRUs("loanThread", Organisation, sem);
		System.out.println("Loan company object created");
		Thread l = new Thread(loanCompany.getThreadGroup(), loanCompany);
		System.out.println("Loan company thread created");
		l.start();
		
		UoW university = new UoW("universityThread", Organisation, sem);
		System.out.println("University object created");
		Thread u = new Thread(university.getThreadGroup(), university);
		System.out.println("University thread created");
		u.start();
		
		try {
			s.join();
			g.join();
			l.join();
			u.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		studentAccount.printStatement();
	}

}
