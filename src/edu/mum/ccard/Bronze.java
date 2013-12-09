package edu.mum.ccard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Bronze extends CreditCard {

	private double minimumPayment;
	
	public Bronze(String accountNumber, Date expDate) {
		super(accountNumber, expDate);
		interest = 0.10;
		minimumPayment = 0.14;
	}
	
	public Vector<String> getDatas() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
		Vector<String> datas = new Vector<String>();
		datas.add(customer.getName());
		datas.add(super.getAccountNumber());
		datas.add(sdf.format(expDate));
		datas.add("Bronze");
		datas.add(String.format("%.2f", balance));
		return datas;
	}

	@Override
	public String[] toArray() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String[] s = {"Bronze", customer.getEmail(), super.accountNumber, "" + balance, sdf.format(expDate), minimumPayment + ""};
		return s;
	}
	
}
