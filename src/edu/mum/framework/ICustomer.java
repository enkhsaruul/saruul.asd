package edu.mum.framework;

import java.util.List;

import edu.mum.framework.gui.GUIDialog;
import edu.mum.framework.mail.Mail;

public interface ICustomer  {
	
	public void sendEmail(String title, String text);
	public void addAccount(IAccount account);

	public String getName();
	public List<IAccount> getAccountList();
	public Address getAddress();
	public String getEmail();
	public String getCustomerType();
	public String[] toArray();
}
