package synch1MWdeadlock;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * A bank with a number of bank accounts that uses locks for serializing access.
 */
public class Bank {
	private final double[] accounts;
	private Lock bankLock;
	private Condition sufficientFunds;

	/**
	 * Constructs the bank.
	 * 
	 * @param n              the number of accounts
	 * @param initialBalance the initial balance for each account
	 */
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		Arrays.fill(accounts, initialBalance);
		bankLock = new ReentrantLock();
		sufficientFunds = bankLock.newCondition();
	}

	/**
	 * Transfers money from one account to another.
	 * 
	 * @param from   the account to transfer from
	 * @param to     the account to transfer to
	 * @param amount the amount to transfer
	 */
//	public void transfer(int from, int to, double amount) throws InterruptedException {
//		bankLock.lock();
//		try {
//			while (accounts[from] < amount)
//				sufficientFunds.await();
//			System.out.print(Thread.currentThread());
//			accounts[from] -= amount;
//			System.out.printf(" %10.2f from %d to %d", amount, from, to);
//			accounts[to] += amount;
//			System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
//			sufficientFunds.signalAll();
//		} finally {
//			bankLock.unlock();
//		}
//	}
	
	public void transfer(int from, int to, double amount) throws InterruptedException {
		bankLock.lock();
		try {
			while (accounts[from] < amount)
				sufficientFunds.await(10, TimeUnit.MILLISECONDS);
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
			sufficientFunds.signalAll();
		} finally {
			bankLock.unlock();
		}
	}

	/**
	 * Gets the sum of all account balances.
	 * 
	 * @return the total balance
	 */
	public double getTotalBalance() {
		bankLock.lock();
		try {
			double sum = 0;

			for (double a : accounts)
				sum += a;

			return sum;
		} finally {
			bankLock.unlock();
		}
	}

	/**
	 * Gets the number of accounts in the bank.
	 * 
	 * @return the number of accounts
	 */
	public int size() {
		return accounts.length;
	}

//	public double[] getAccounts() {
//		return accounts;
//	}

	public void addInterest1(int accountID, double interest) {

		bankLock.lock();
		try {
			System.out.print("Interest: ");
			System.out.print(Thread.currentThread());
			accounts[accountID] += interest;
			System.out.printf("  %10.2f add to account %d", interest, accountID);
			System.out.printf("  Account %d balance: %10.2f", accountID, accounts[accountID]);
			System.out.printf("  Total Balance: %10.2f%n", getTotalBalance());
			
		} finally {
			bankLock.unlock();
		}
	}
	
	public void addInterest2(int accountID, double interest, double limit) throws InterruptedException {

		bankLock.lock();
		try {
			while (accounts[accountID] < limit)
				sufficientFunds.await();
			
			System.out.print("Interest: ");
			System.out.print(Thread.currentThread());
			accounts[accountID] += interest;
			System.out.printf("  %10.2f add to account %d", interest, accountID);
			System.out.printf("  Account %d balance: %10.2f", accountID, accounts[accountID]);
			System.out.printf("  Total Balance: %10.2f%n", getTotalBalance());
			
			sufficientFunds.signalAll();
			
		} finally {
			bankLock.unlock();
		}
	}
	
	public void addInterest3(int accountID, double interest, double limit) throws InterruptedException {

		bankLock.lock();
		try {
			while (accounts[accountID] < limit)
				sufficientFunds.await(10, TimeUnit.MILLISECONDS);
			
			System.out.print("Interest: ");
			System.out.print(Thread.currentThread());
			accounts[accountID] += interest;
			System.out.printf("  %10.2f add to account %d", interest, accountID);
			System.out.printf("  Account %d balance: %10.2f", accountID, accounts[accountID]);
			System.out.printf("  Total Balance: %10.2f%n", getTotalBalance());
			
			sufficientFunds.signalAll();
			
		} finally {
			bankLock.unlock();
		}
	}

}
