package edu.mum.framework;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommandDeposit implements ICommand {

	private String commandDescription = null;
	private IAccount account = null;
	private Calendar date = Calendar.getInstance();
	private double amount;
	private FunctorDeposit functor;
	
	public CommandDeposit(IAccount account, double amount, FunctorDeposit functor) {
		this.account = account;
		this.functor = functor;
		this.amount = amount;
		this.commandDescription = "Deposit";
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
		int result = functor.execute(this);

		if (result == TransactionResult.NORMAL) {
			account.deposit(amount);
		}
		else if (result == TransactionResult.LARGE_AMOUNT) {
			account.deposit(amount);
			account.getCustomer().sendEmail(
					"Transaction notification",
					"The following transaction has been made to your account.\n\n" +
					toString()
					);
		}
		
	}
	
	public String toString() {
		String s = "";
		s += commandDescription + "\n";
		s += new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date.getTime()) + "\n";
		s += "Amount: $" + amount + "\n";
		return s;
	}

	@Override
	public String[] toArray() {
		String[] s = {commandDescription, "" + account.getAccountNumber(), "" + amount, new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date.getTime())};
		return s;
	}

	public void setDate(Date date) {
		this.date.setTime(date);
	}

}
