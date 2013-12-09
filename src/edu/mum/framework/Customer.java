package edu.mum.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import edu.mum.framework.gui.GUIDialog;
import edu.mum.framework.mail.EMail;
import edu.mum.framework.mail.Mail;

public class Customer implements ICustomer {
	
	protected List<IAccount> accountList;
	protected Address address;
	protected String name;
	protected String email;
	
	public Customer(Address address, String name, String email) {
		this.address = address;
		this.name = name;
		this.email = email;
		accountList = new ArrayList<IAccount>();
	}

	@Override
	public void addAccount(IAccount account) {
		account.setCustomer(this);
		accountList.add(account);
	}

	@Override
	public void sendEmail(String title, String text) {
		Mail mail = new Mail("hasanaa.88@gmail.com", email, title, text);
		EMail emailSender = EMail.getInstance();
		emailSender.sendMail(mail);
	}

	@Override
	public List<IAccount> getAccountList() {
		return accountList;
	}

	public String toString() {
		String s = "";
		s += "Name= " + name + "\n";
		s += "Address= " + address.toString() + "\n";
		return s;
	}

	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public String getCustomerType() {
		return "Default";
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return name;
	}
}
