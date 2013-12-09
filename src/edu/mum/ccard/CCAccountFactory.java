package edu.mum.ccard;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import edu.mum.bank.Checking;
import edu.mum.bank.Saving;
import edu.mum.framework.AccountFactory;
import edu.mum.framework.IAccount;
import edu.mum.framework.gui.GUIDialog;

public class CCAccountFactory extends AccountFactory {

	public IAccount createAccountFromDB(String[] s) {
		IAccount account = null;
		try {
			if (s[0].equals("Gold")) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				account = new Gold(s[2], sdf.parse(s[4]));
				account.deposit(Double.parseDouble(s[3]));
			}
			else if (s[0].equals("Silver")){
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				account = new Silver(s[2], sdf.parse(s[4]));
				account.deposit(Double.parseDouble(s[3]));
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				account = new Bronze(s[2], sdf.parse(s[4]));
				account.deposit(Double.parseDouble(s[3]));
			}
		}
		catch(Exception ex) {
			
		}
		return account;
	}
	
	public IAccount createAccountFromDialog(GUIDialog dialog) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		IAccount account = null;
		try {
			Date date = sdf.parse(dialog.getValues().get(7));
			if (dialog.getSelectedIndex() == 0) { 
				account = new Gold(dialog.getValues().get(6), date);
			}
			else if (dialog.getSelectedIndex() == 1) {
				account = new Silver(dialog.getValues().get(6), date);
			} else {
				account = new Bronze(dialog.getValues().get(6), date);
			}
		} catch(Exception ex) {
			
		}
		return account;
	}
}
