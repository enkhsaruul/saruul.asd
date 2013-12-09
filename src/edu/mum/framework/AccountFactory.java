package edu.mum.framework;

import edu.mum.framework.gui.GUIDialog;

public class AccountFactory {
	public IAccount createAccountFromDB(String[] s) {
		IAccount account = new Account(s[2]);
		account.deposit(Double.parseDouble(s[3]));
		return account;
	}
	
	public IAccount createAccountFromDialog(GUIDialog dialog) {
		IAccount account = new Account((10000000 + new java.util.Random().nextInt(90000000)) + "");
		return account;
	}
}
