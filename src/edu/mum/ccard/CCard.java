package edu.mum.ccard;

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
import edu.mum.framework.gui.GUIReport;
import edu.mum.framework.gui.GUITransaction;
import edu.mum.framework.*;

public class CCard {
	
	public static void main(String args[]) {
		CCardGUI bank = new CCardGUI("CreditCard");
		bank.run();
	}
}