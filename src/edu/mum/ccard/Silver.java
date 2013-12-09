package edu.mum.ccard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Silver extends CreditCard {

	private double minimumPayment;
	
	public Silver(String accountNumber, Date expDate) {
		super(accountNumber, expDate);
		interest = 0.08;
		minimumPayment = 0.12;
	}
	
	public Vector<String> getDatas() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
		Vector<String> datas = new Vector<String>();
		datas.add(customer.getName());
		datas.add(super.getAccountNumber());
		datas.add(sdf.format(expDate));
		datas.add("Silver");
		datas.add(String.format("%.2f", balance));
		return datas;
	}

	@Override
	public String[] toArray() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String[] s = {"Silver", customer.getEmail(), super.accountNumber, "" + balance, sdf.format(expDate), minimumPayment + ""};
		return s;
	}
	
}
