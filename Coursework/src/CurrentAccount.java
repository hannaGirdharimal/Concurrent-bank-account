
public class CurrentAccount implements BankAccount {

	private static int balance = 1000;
	private static String accountHolder = "Jake";
	private static int accountNo = 109375860;
	private static Statement accountStatement = new Statement(accountHolder, accountNo);

	@Override
	public int getBalance() {
		return balance;
	}

	@Override
	public int getAccountNumber() {
		return accountNo;
	}

	@Override
	public String getAccountHolder() {
		return accountHolder;
	}

	static final Object lock = new Object();

	@Override
	public void deposit(Transaction t) {
		synchronized (lock) {
			balance += t.getAmount();
			accountStatement.addTransaction(t.getCID(), t.getAmount(), balance);
		}
	}

	@Override
	public void withdrawal(Transaction t) {
		synchronized (lock) {
			while (isOverdrawn(t)) {
				try {
					// System.out.println("Insufficient funds to make withdrawal");
					lock.wait(2000);
					lock.notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			balance -= t.getAmount();
			accountStatement.addTransaction(t.getCID(), t.getAmount(), balance);
		}
	}

	@Override
	public boolean isOverdrawn(Transaction t) {
		if (balance < t.getAmount()) {
			return true;
		} else
			return false;
	}

	@Override
	public void printStatement() {
		accountStatement.print();
	}

}
