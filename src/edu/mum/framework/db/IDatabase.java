package edu.mum.framework.db;

import java.util.List;

import edu.mum.framework.IAccount;
import edu.mum.framework.ICommand;
import edu.mum.framework.ICustomer;

public interface IDatabase {
	
	public void readDatas();
	public void writeDatas();
}
