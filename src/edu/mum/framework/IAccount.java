package edu.mum.framework;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public interface IAccount extends Observer {
	
	public String getAccountNumber();
	public void addInterest();
	public double getInterest();
	public double getCurrentBalance();
	public ICustomer getCustomer();
	public void setCustomer(ICustomer customer);
	public void withdraw(double amount);
	public void deposit(double amount);
	public String getBill();
	public Vector<String> getDatas();
	public String[] toArray();
	public List<ICommand> getTransactions();
	public void executeCommand(ICommand command);
}
