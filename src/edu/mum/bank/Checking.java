package edu.mum.bank;


import java.util.Vector;

import edu.mum.framework.Account;

public class Checking extends Account {
	
	public Checking(String accountNumber) {
		super(accountNumber);
		interest = 0.01;
	}

	public Checking(String accountNumber, double interest) {
		super(accountNumber, interest);
	}

	public Vector<String> getDatas() {
		Vector<String> datas = new Vector<String>();
		datas.add(customer.getAddress().getStreet());
		datas.add(customer.getAddress().getCity());
		datas.add(customer.getAddress().getState());
		datas.add(customer.getAddress().getZip());
		datas.add(customer.getCustomerType());
		datas.add("Ch");
		datas.add("" + Math.round(balance * 100) / 100.0);
		return datas;
	}

	@Override
	public String[] toArray() {
		String[] s = {"Checking", customer.getEmail(), accountNumber, "" + balance};
		return s;
	}
}
