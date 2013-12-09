package edu.mum.ccard;

import java.util.Date;

import edu.mum.framework.Account;

public class CreditCard extends Account {

	protected Date expDate;
	
	public CreditCard(String accountNumber, Date expDate) {
		super(accountNumber);
		this.expDate = expDate;
	}
	
	
}
