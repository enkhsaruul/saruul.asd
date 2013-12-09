package edu.mum.framework.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class GUITransaction extends JDialog implements ActionListener {
	
	private static final int WIDTH = 15;
	
	private static final int MARGIN_RIGHT = 5;
	private static final int MARGIN_LEFT = 5;
	private static final int MARGIN_TOP = 10;
	private static final int MARGIN_BOTTOM = 10;
	
	private JTextField txtName;
	private JTextField txtAmount;
	
	private JButton btnOk;
	private JButton btnCancel;
	
	private String name = null;
	private String amount = null;
	
	public String getName() {
		return name;
	}
	
	public String getAmount() {
		return amount;
	}
	
	/**
	 * Constructor of the GUITransaction.
	 * 
	 * @param frameTitle name of the dialog
	 */
	public GUITransaction(String dialogTitle, JFrame frame) {
		super(frame, true);
		
		this.setTitle(dialogTitle);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel pnlInput = new JPanel();
		
		pnlInput.setLayout(new GridLayout(2, 2));
		
		txtName = new JTextField(WIDTH);
		txtAmount = new JTextField(WIDTH);
		
		Border current = pnlInput.getBorder();
		Border empty = new EmptyBorder(MARGIN_TOP, MARGIN_LEFT, MARGIN_BOTTOM, MARGIN_RIGHT);
		if (current == null) {
			pnlInput.setBorder(empty);
		} else {
			pnlInput.setBorder(new CompoundBorder(empty, current));
		}
		
		pnlInput.add(new JLabel("Name : "));
		pnlInput.add(txtName);
		pnlInput.add(new JLabel("Amount : "));
		pnlInput.add(txtAmount);
		
		JPanel pnlButton = new JPanel();
		pnlButton.setLayout(new FlowLayout());
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		
		pnlButton.add(btnOk);
		pnlButton.add(btnCancel);
		
		this.setLocationRelativeTo(frame);
		this.add(pnlInput);
		this.add(pnlButton);
		this.pack();		
		this.setVisible(true);
	}
	
	public static void main(String [] args) {
		//GUITransaction gui = new GUITransaction("XaCaHaa");
		//gui.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnOk) {
			this.name = txtName.getText();
			this.amount = txtAmount.getText();
			this.dispose();
		} 
		if(ae.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
