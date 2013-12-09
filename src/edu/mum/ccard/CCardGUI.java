package edu.mum.ccard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import edu.mum.framework.Account;
import edu.mum.framework.AccountFactory;
import edu.mum.framework.CustomerFactory;
import edu.mum.framework.Finance;
import edu.mum.framework.FincoGUI;
import edu.mum.framework.FunctorDeposit;
import edu.mum.framework.FunctorWithdraw;
import edu.mum.framework.IAccount;
import edu.mum.framework.ICustomer;
import edu.mum.framework.IFinance;
import edu.mum.framework.db.DatabaseHandler;
import edu.mum.framework.db.IDatabase;
import edu.mum.framework.gui.GUIContainer;
import edu.mum.framework.gui.GUIDialog;
import edu.mum.framework.gui.GUIReport;
import edu.mum.framework.gui.GUITransaction;

public class CCardGUI extends FincoGUI {
	
	public CCardGUI(String frameTitle) {
		super(frameTitle, columnNames);
		// TODO Auto-generated constructor stub
	}

	private List<IAccount> accountMap;
	private static String [] columnNames = new String[] {"Name", "CC number", "Exp date", "Type", "Balance"};
	private IFinance finance;
	
	protected JButton btnDeposit;
	protected JButton btnCharge;
	protected JButton btnAddCreditCard;
	protected JButton btnMonthlyBill;
	protected JButton btnExit;

	private IDatabase database;
	private AccountFactory accountFactory;
	
	private GUIContainer getInsance() {
		return this;
	}
	
	private void createButtonsAndAdd() {		
		btnDeposit = new JButton("Deposit");
		btnCharge = new JButton("Charge");
		btnAddCreditCard = new JButton("Add Credit-card account");
		btnMonthlyBill = new JButton("Generate Monthly bills");
		btnExit = new JButton("Exit");
		
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				database.writeDatas();
				getInsance().dispose();
			}
		});
		btnAddCreditCard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIDialog dialog = new GUIDialog(
						"Add Person", 
						getInsance(), 
						new String[]{"Name", "Street", "City", "State", "Zip", "Email", "CC number", "Exp. date"},
						new String[]{"Gold", "Silver", "Bronze"});
				if(dialog.getOkPressed()) {
					finance.addAccount(
							CustomerFactory.createCustomerFromDialog("Person", dialog),
							accountFactory.createAccountFromDialog(dialog)
							);
					refreshTable();
				}
			}
		});
		btnCharge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int idx = getInsance().getSelectedRowIndex();
					if(idx == -1) {
						JOptionPane.showMessageDialog(getInsance(), "Please select an Account!", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
					GUITransaction guiWithdraw = new GUITransaction("Withdraw", getInsance());
					String name = null;
					double amount = 0.0;
					if(guiWithdraw.getName() != null && guiWithdraw.getAmount() != null) {
						name = guiWithdraw.getName();
						amount = Double.parseDouble(guiWithdraw.getAmount());
					}
				
					finance.withdraw(accountMap.get(idx), amount, new FunctorWithdraw(400));
					refreshTable();
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(getInsance(), "Amount should be in number format.", "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnMonthlyBill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				StringBuilder report = new StringBuilder();
				for(IAccount a : accountMap) {
					report.append(Account.getReport(a));
					report.append("\n");
				}
				GUIReport guiReport = new GUIReport("Report", getInsance(), report.toString());
				refreshTable();
			}
		});
		btnDeposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					int idx = getInsance().getSelectedRowIndex();
					if(idx == -1) {
						JOptionPane.showMessageDialog(getInsance(), "Please select an Account!", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
					GUITransaction guiDeposit = new GUITransaction("Deposit", getInsance());
					String name = null;
					double amount = 0.0;
					if(guiDeposit.getName() != null && guiDeposit.getAmount() != null) {
						name = guiDeposit.getName();
						amount = Double.parseDouble(guiDeposit.getAmount());
					}
				
					finance.deposit(accountMap.get(idx), amount, new FunctorDeposit(400));
					refreshTable();
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(getInsance(), "Amount should be in number format.", "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		this.addButtonAtTop(btnAddCreditCard);
		this.addButtonAtTop(btnMonthlyBill);
		this.addButtonAtRight(btnDeposit);
		this.addButtonAtRight(btnCharge);
		this.addButtonAtRight(btnExit);
		this.pack();
	}
	
	private void refreshTable() {
		this.clearTable();
		accountMap.clear();
		for(ICustomer c : finance.getCustomers()) {
			for(IAccount a : c.getAccountList()) {
				this.addRow(a.getDatas());
				accountMap.add(a);
			}
		}
	}
	
	public void run() {
		accountMap = new ArrayList<IAccount>();
		finance = new Finance();
		//gui = new GUIContainer("Credit Card", columnNames);
		
		accountFactory = new CCAccountFactory();
		database = new DatabaseHandler(accountFactory, finance);
		database.readDatas();
		this.refreshTable();
		
		createButtonsAndAdd();
	}
}
