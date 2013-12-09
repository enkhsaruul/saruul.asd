package edu.mum.framework;

import java.util.List;

public interface IFinance {
	public void addInterest();
	public void withdraw(IAccount account, double amount, FunctorWithdraw functor);
	public void deposit(IAccount account, double amount, FunctorDeposit functor);
	public void addAccount(String email, IAccount account);
	public void addAccount(ICustomer customer, IAccount account);
	public List<ICustomer> getCustomers();
}
