package edu.mum.bank;

import java.util.Vector;

import edu.mum.framework.Account;
import edu.mum.framework.ICustomer;



public class Saving extends Account {
	
	public Saving(String accountNumber) {
		super(accountNumber);
		interest = 0.0325;
	}
	
	public Saving(String accountNumber, double interest) {
		super(accountNumber, interest);
		// TODO Auto-generated constructor stub
	}

	public Vector<String> getDatas() {
		Vector<String> datas = new Vector<String>();
		datas.add(customer.getAddress().getStreet());
		datas.add(customer.getAddress().getCity());
		datas.add(customer.getAddress().getState());
		datas.add(customer.getAddress().getZip());
		datas.add(customer.getCustomerType());
		datas.add("S");
		datas.add("" + Math.round(balance * 100) / 100.0);
		return datas;
	}

	@Override
	public String[] toArray() {
		String[] s = {"Saving", customer.getEmail(), accountNumber, "" + balance};
		return s;
	}
}
