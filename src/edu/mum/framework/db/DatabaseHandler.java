package edu.mum.framework.db;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import edu.mum.framework.AccountFactory;
import edu.mum.framework.Address;
import edu.mum.framework.CommandDeposit;
import edu.mum.framework.CommandInterest;
import edu.mum.framework.CommandWithdraw;
import edu.mum.framework.Company;
import edu.mum.framework.Customer;
import edu.mum.framework.Finance;
import edu.mum.framework.IAccount;
import edu.mum.framework.ICommand;
import edu.mum.framework.ICustomer;
import edu.mum.framework.IFinance;
import edu.mum.framework.Person;

public final class DatabaseHandler implements IDatabase {
	
	IFinance finance;
	AccountFactory accountFactory;
	
	private CSVWriter customerWriter = null;
	private CSVWriter accountWriter = null;
	private CSVWriter transactionWriter = null;
	
	public DatabaseHandler(AccountFactory accountFactory, IFinance finance) {
		this.finance = finance;
		this.accountFactory = accountFactory;
	}
	
	@Override
	public final void readDatas() {
		List<ICustomer> customers = finance.getCustomers();
		
		readCustomer(customers);
		readAccount();
		readTransaction();
	}

	@Override
	public final void writeDatas() {
		try {
			customerWriter = new CSVWriter(new FileWriter("data/customers.csv"), '\t');
			transactionWriter = new CSVWriter(new FileWriter("data/transactions.csv"), '\t');
			accountWriter = new CSVWriter(new FileWriter("data/accounts.csv"), '\t');
			
			
			List<ICustomer> customers = finance.getCustomers();
			
			for (ICustomer c : customers) {
				writeCustomer(c);
				for (IAccount account : c.getAccountList()) {
					writeAccount(account);
				}
			}
			
			
			for (ICustomer customer : finance.getCustomers())
				for (IAccount account : customer.getAccountList())
					for (ICommand c : account.getTransactions())
						writeTransaction(c);
			
			customerWriter.close();
			transactionWriter.close();
			accountWriter.close();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	private String[] convertFromTransaction(ICommand transaction) {
		return transaction.toArray();
	}
	
	private void writeTransaction(ICommand transaction) {
		transactionWriter.writeNext(convertFromTransaction(transaction));
	}
	
	private String[] convertFromCustomer(ICustomer customer) {
		return customer.toArray();
	}

	private void writeCustomer(ICustomer customer) {
		customerWriter.writeNext(convertFromCustomer(customer));
	}
	
	private String[] convertFromAccount(IAccount account) {
		return account.toArray();
	}

	private void writeAccount(IAccount account) {
		accountWriter.writeNext(convertFromAccount(account));
	}
	
	private ICustomer convertToCustomer(String[] s) {
		if (s[0].equals("Person")) {
			return new Person(new Address(s[4], s[6], s[5], s[3]), s[1], s[2], s[7]);
		}
		else if (s[0].equals("Company")) {
			return new Company(new Address(s[4], s[6], s[5], s[3]), s[1], s[2], Integer.parseInt(s[7])); 
		}
		return null;
	}

	private void readCustomer(List<ICustomer> customers) {
		try {
			CSVReader reader = new CSVReader(new FileReader("data/customers.csv"), '\t');
		    String [] nextLine;
		    while ((nextLine = reader.readNext()) != null) {
		    	customers.add(convertToCustomer(nextLine));
		    }
		    reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void readAccount() {
		try {
			CSVReader reader = new CSVReader(new FileReader("data/accounts.csv"), '\t');
		    String [] nextLine;
		    while ((nextLine = reader.readNext()) != null) {
		    	finance.addAccount(nextLine[1], accountFactory.createAccountFromDB(nextLine));
		    }
		    reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}	

	private ICommand convertToTransaction(IAccount account, String[] s) {
		if (s[0].equals("Deposit")) {
			CommandDeposit command = new CommandDeposit(account, Double.parseDouble(s[2]), null);
			try {
				command.setDate(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(s[3]));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return command;
		}
		else if (s[0].equals("Withdraw")) {
			CommandWithdraw command = new CommandWithdraw(account, Double.parseDouble(s[2]), null);
			try {
				command.setDate(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(s[3]));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return command;
		}
		else {
			CommandInterest command = new CommandInterest(account);
			try {
				command.setDate(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(s[2]));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return command;
		}
	}
	
	private void readTransaction() {
		try {
			CSVReader reader = new CSVReader(new FileReader("data/transactions.csv"), '\t');
		    String [] nextLine;
		    while ((nextLine = reader.readNext()) != null) {
		    	IAccount account = null;
		    	for (ICustomer c : finance.getCustomers())
			    	for (IAccount a : c.getAccountList())
			    		if (nextLine[1].equals(a.getAccountNumber())) {
			    			account = a;
			    		}
		    	ICommand command = convertToTransaction(account, nextLine);
		    	account.getTransactions().add(command);
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
