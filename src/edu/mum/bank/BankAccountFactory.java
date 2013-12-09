package edu.mum.bank;

import edu.mum.framework.Account;
import edu.mum.framework.AccountFactory;
import edu.mum.framework.IAccount;
import edu.mum.framework.gui.GUIDialog;

public class BankAccountFactory extends AccountFactory {

	public IAccount createAccountFromDB(String[] s) {
		IAccount account = null;
		if (s[0].equals("Checking")) {
			account = new Checking(s[2]);
			account.deposit(Double.parseDouble(s[3]));
		}
		else {
			account = new Saving(s[2]);
			account.deposit(Double.parseDouble(s[3]));
		}
		return account;
	}
	
	public IAccount createAccountFromDialog(GUIDialog dialog) {
		IAccount account = null;
		if (dialog.getSelectedIndex() == 0) { 
			account = new Checking((10000000 + new java.util.Random().nextInt(90000000)) + "");
		}
		else {
			account = new Saving((10000000 + new java.util.Random().nextInt(90000000)) + "");
		}
		return account;
	}
}
