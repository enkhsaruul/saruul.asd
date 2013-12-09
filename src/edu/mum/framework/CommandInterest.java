package edu.mum.framework;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommandInterest implements ICommand {

	private String commandDescription = null;
	private IAccount account = null;
	private Calendar date = Calendar.getInstance();
	private double amount;
	
	public CommandInterest(IAccount account) {
		this.account = account;
		this.commandDescription = "Interest";
	}
	
	public void setCommandDescription(String commandDescription) {
		this.commandDescription = commandDescription;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public IAccount getAccount() {
		return account;
	}
	
	@Override
	public void execute() {
		account.addInterest();
	}
	
	public String toString() {
		String s = "";
		s += commandDescription + "\n";
		s += new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date.getTime()) + "\n";
		s += "Amount: $" + account.getCurrentBalance() * account.getInterest() + "\n";
		return s;
	}

	@Override
	public String[] toArray() {
		String[] s = {commandDescription, "" + account.getAccountNumber(), new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date.getTime())};
		return s;
	}

	public void setDate(Date date) {
		this.date.setTime(date);
	}

}
