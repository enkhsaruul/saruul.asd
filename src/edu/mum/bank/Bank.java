package edu.mum.bank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import edu.mum.framework.db.DatabaseHandler;
import edu.mum.framework.db.IDatabase;
import edu.mum.framework.gui.GUIDialog;
import edu.mum.framework.gui.GUIContainer;
import edu.mum.framework.gui.GUITransaction;
import edu.mum.framework.*;

public class Bank {
	
	public static void main(String args[]) {
		BankGUI bank = new BankGUI("Bank");
		bank.run();
	}
}
