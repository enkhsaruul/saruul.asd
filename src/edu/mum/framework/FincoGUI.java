package edu.mum.framework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import edu.mum.framework.db.DatabaseHandler;
import edu.mum.framework.db.IDatabase;
import edu.mum.framework.gui.GUIContainer;
import edu.mum.framework.gui.GUIDialog;
import edu.mum.framework.gui.GUITransaction;

public class FincoGUI extends GUIContainer {

	private List<IAccount> accountMap;
	//private static String [] columnNames = new String[] {"Street", "City", "State", "Zip", "P/C", "Amount"};
	private IFinance finance;
	
	protected JButton btnDeposit;
	protected JButton btnWithdraw;
	protected JButton btnAccountPerson;
	protected JButton btnAccountCompany;
	protected JButton btnAddInterest;
	protected JButton btnExit;

	private IDatabase database;
	private AccountFactory accountFactory;
	
	public FincoGUI(String frameTitle, String [] columnNames) {
		super(frameTitle, columnNames);
	}
	
	private GUIContainer getInstance() {
		return this;
	}
	
	private void createButtonsAndAdd() {
		btnAddInterest = new JButton("Add Interest");
		btnDeposit = new JButton("Deposit");
		btnWithdraw = new JButton("Withdraw");
		btnAccountPerson = new JButton("Add Personal Account");
		btnAccountCompany = new JButton("Add Company Account");
		btnExit = new JButton("Exit");
		
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				database.writeDatas();
				getInstance().dispose();
			}
		});
		btnAccountCompany.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				GUIDialog dialog = new GUIDialog(
						"Add Person", 
						getInstance(), 
						new String[]{"Name", "Street", "City", "State", "Zip", "No of employees", "Email"},
						new String[]{"Default"});
				if(dialog.getOkPressed()) {
					finance.addAccount(
							CustomerFactory.createCustomerFromDialog("Company", dialog),
							accountFactory.createAccountFromDialog(dialog)
							);
					refreshTable();
				}
			}
		});
		btnAccountPerson.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIDialog dialog = new GUIDialog(
						"Add Person", 
						getInstance(), 
						new String[]{"Name", "Street", "City", "State", "Zip", "Birthdate", "Email"},
						new String[]{"Default"});
				if(dialog.getOkPressed()) {
					finance.addAccount(
							CustomerFactory.createCustomerFromDialog("Person", dialog),
							accountFactory.createAccountFromDialog(dialog)
							);
					refreshTable();
				}
			}
		});
		btnWithdraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int idx = getInstance().getSelectedRowIndex();
					if(idx == -1) {
						JOptionPane.showMessageDialog(getInstance(), "Please select an Account!", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
					GUITransaction guiWithdraw = new GUITransaction("Withdraw", getInstance());
					String name = null;
					double amount = 0.0;
					if(guiWithdraw.getName() != null && guiWithdraw.getAmount() != null) {
						name = guiWithdraw.getName();
						amount = Double.parseDouble(guiWithdraw.getAmount());
					}
				
					finance.withdraw(accountMap.get(idx), amount, new FunctorWithdraw(400));
					refreshTable();
					
				} catch(Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(getInstance(), "Amount should be in number format.", "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddInterest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				finance.addInterest();
				refreshTable();
			}
		});
		btnDeposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					int idx = getInstance().getSelectedRowIndex();
					if(idx == -1) {
						JOptionPane.showMessageDialog(getInstance(), "Please select an Account!", "Warning", JOptionPane.WARNING_MESSAGE);
						return;
					}
					GUITransaction guiDeposit = new GUITransaction("Deposit", getInstance());
					String name = null;
					double amount = 0.0;
					if(guiDeposit.getName() != null && guiDeposit.getAmount() != null) {
						name = guiDeposit.getName();
						amount = Double.parseDouble(guiDeposit.getAmount());
					}
				
					finance.deposit(accountMap.get(idx), amount, new FunctorDeposit(400));
					refreshTable();
					
				} catch(Exception e) {
					JOptionPane.showMessageDialog(getInstance(), "Amount should be in number format.", "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		this.addButtonAtTop(btnAccountPerson);
		this.addButtonAtTop(btnAccountCompany);
		this.addButtonAtTop(btnAddInterest);
		this.addButtonAtRight(btnDeposit);
		this.addButtonAtRight(btnWithdraw);
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
		
		accountFactory = new AccountFactory();
		database = new DatabaseHandler(accountFactory, finance);
		database.readDatas();
		this.refreshTable();
		
		createButtonsAndAdd();
	}
	
	

}
