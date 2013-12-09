package edu.mum.framework;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Vector;

import edu.mum.framework.gui.GUIDialog;


public class Account implements IAccount {
	
	protected ICustomer customer;
	protected String accountNumber;
	protected double balance;
	protected double interest;
	protected List<ICommand> transactions = new ArrayList<ICommand>();
	
	public Account(String accountNumber) {
		this.accountNumber = accountNumber;
		interest = 0.01;
		balance = 0;
	}
	
	public Account(String accountNumber, double interest) {
		this.accountNumber = accountNumber;
		this.interest = interest;
		balance = 0;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public void notifyByEmail() {
	}

	@Override
	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public void addInterest() {
		balance *= (1 + interest);
	}
	
	public double getInterest() {
		return interest;
	}
	
	public ICustomer getCustomer() {
		return customer;
	}
	
	public void setCustomer(ICustomer customer) {
		this.customer = customer;
	}
	
	public double getCurrentBalance() {
		return balance;
	}

	@Override
	public void withdraw(double amount) {
		balance -= amount;
	}

	@Override
	public void deposit(double amount) {
		balance += amount;
	}

	public static String getReport(IAccount a) {
		String bill = "";
		bill += "Name : " + a.getCustomer().getName() + "\n";
		bill += "Address : " + a.getCustomer().getAddress().toString() + "\n";
		bill += "CC number : " + a.getAccountNumber() + "\n";
		bill += "Type : " + a.toArray()[0] + "\n";
		bill += "Balance : " + a.getCurrentBalance() + "\n";
		bill += "Total amount due : " + a.getCurrentBalance() * Double.parseDouble(a.toArray()[5]);
		return bill;
	}
	
	@Override
	public String getBill() {
		String bill = "";
		bill += customer.toString() + "\n";
		bill += balance;
		return bill;
	}
	
	public Vector<String> getDatas() {
		Vector<String> datas = new Vector<String>();
		datas.add(customer.getAddress().getStreet());
		datas.add(customer.getAddress().getCity());
		datas.add(customer.getAddress().getState());
		datas.add(customer.getAddress().getZip());
		datas.add(customer.getCustomerType());
		datas.add("" + Math.round(balance * 100) / 100.0);
		return datas;
	}

	@Override
	public String[] toArray() {
		String[] s = {"Default", customer.getEmail(), accountNumber, "" + balance};
		return s;
	}

	@Override
	public List<ICommand> getTransactions() {
		return transactions;
	}
	
	public void executeCommand(ICommand command) {
		transactions.add(command);
		command.execute();
	}

}
