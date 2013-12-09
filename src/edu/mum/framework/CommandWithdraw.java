package edu.mum.framework;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommandWithdraw implements ICommand {

	private String commandDescription = null;
	private IAccount account = null;
	private Calendar date = Calendar.getInstance();
	private double amount;
	private FunctorWithdraw functor;
	
	public CommandWithdraw(IAccount account, double amount, FunctorWithdraw functor) {
		this.account = account;
		this.amount = amount;
		this.commandDescription = "Withdraw";
		this.functor = functor;
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

		if (result == TransactionResult.NOT_ENOUGH_CREDIT) {
			account.getCustomer().sendEmail(
					"Transaction failure notification",
					"The following transaction has failed because your balance was not enough.\n\n" +
					toString()
					);	
		}
		else if (result == TransactionResult.NORMAL) {
			account.withdraw(amount);
		}
		else if (result == TransactionResult.LARGE_AMOUNT) {
			account.withdraw(amount);
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
