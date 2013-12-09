package edu.mum.framework.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUITester extends JFrame implements ActionListener {
	
	private JButton btn;
	
	public GUITester() {
		super("Tester");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btn = new JButton("Press");
		btn.addActionListener(this);
		this.add(btn);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String [] args) {
		new GUITester();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn) {
			System.out.println("Button Pressed");
			
			/*GUITransaction gui = new GUITransaction("Deposit", this);
			
			System.out.println(gui.getName());
			System.out.println(gui.getAmount());*/
			
			
			/*GUIAccountDialog gui = new GUIAccountDialog(
					"Test", 
					this, 
					new String[]{"Name", "Street", "City", "State", "Zip", "Email", "CC Number", "Exp. Date"},
					new String[]{"Gold", "Silver", "Bronze"});
			
			System.out.println("Selected index : " + gui.getSelectedIndex());
			System.out.println("Values : " + gui.getValues());*/
			
			GUIReport gui = new GUIReport("Report", this, "fhdsaflhdsjafhkdslk\nfdsafdjsafdsafjhk");
			
			
			System.out.println("Done");
		}
	}
}	
