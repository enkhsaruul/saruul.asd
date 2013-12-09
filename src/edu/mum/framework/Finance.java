package edu.mum.framework;

import java.util.ArrayList;
import edu.mum.framework.db.*;
import java.util.List;
import java.util.Observable;

public class Finance extends Observable implements IFinance {

	private List<ICustomer> customers;
	
	public Finance() {
		customers = new ArrayList<ICustomer>();
	}

	public void addAccount(ICustomer customer, IAccount account) {
		for (ICustomer c: customers) {
			if (c.getEmail().equals(customer.getEmail())) {
				c.addAccount(account);
				return;
			}
		}

		customers.add(customer);
		customer.addAccount(account);
	}
	
	public void addAccount(String email, IAccount account) {
		for (ICustomer c: customers) {
			if (c.getEmail().equals(email)) {
				c.addAccount(account);
				return;
			}
		}
	}	

	@Override
	public void addInterest() {
		for(ICustomer customer : customers) {
			List<IAccount> accounts = customer.getAccountList();
			for(IAccount account : accounts) {
				ICommand command = new CommandInterest(account);
				account.getTransactions().add(command);
				command.execute();
			}
		}
	}


	@Override
	public void withdraw(IAccount account, double amount, FunctorWithdraw functor) {
		ICommand command = new CommandWithdraw(account, amount, functor);
		account.getTransactions().add(command);
		command.execute();
	}

	@Override
	public void deposit(IAccount account, double amount, FunctorDeposit functor) {
		ICommand command = new CommandDeposit(account, amount, functor);
		account.getTransactions().add(command);
		command.execute();
	}
	
	public IAccount findAccount(String accountNumber) {
		for (ICustomer c : customers)
			for (IAccount a : c.getAccountList())
				if (a.getAccountNumber().equals(accountNumber))
					return a;
		return null;
	}
	
	public List<ICustomer> getCustomers() {
		return customers;
	}
}
