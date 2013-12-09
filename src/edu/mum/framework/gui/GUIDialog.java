package edu.mum.framework.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class GUIDialog extends JDialog implements ActionListener {

	private static final int WIDTH = 8;
	
	private static final int MARGIN_RIGHT = 5;
	private static final int MARGIN_LEFT = 5;
	private static final int MARGIN_TOP = 10;
	private static final int MARGIN_BOTTOM = 10;
	
	private JTextField [] txtFields;
	private JRadioButton [] radioButtons;
	
	private JButton btnOk;
	private JButton btnCancel;
	
	private int selectedIndex = -1;
	private List<String> values = null;
	
	private boolean isOkPressed = false;
	
	public boolean getOkPressed() {
		return isOkPressed;
	}
	
	public int getSelectedIndex() {
		return selectedIndex;
	}
	
	public List<String> getValues() {
		return values;
	}
	
	public GUIDialog(String dialogTitle, JFrame frame, String [] fieldNames, String [] buttonNames) {
		super(frame, true);
		this.setTitle(dialogTitle);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel pnlRadionButtons = new JPanel();
		pnlRadionButtons.setLayout(new GridLayout(buttonNames.length, 1));		
		ButtonGroup group = new ButtonGroup();
		radioButtons = new JRadioButton[buttonNames.length];
		for(int i=0;i<radioButtons.length;i++) {
			radioButtons[i] = new JRadioButton(buttonNames[i]);
			if(i == 0)
				radioButtons[i].setSelected(true);
			group.add(radioButtons[i]);
			pnlRadionButtons.add(radioButtons[i]);
		}
		addMargin(pnlRadionButtons);
		
		JPanel pnlInputs = new JPanel();
		pnlInputs.setLayout(new GridLayout(fieldNames.length, 2));
		txtFields = new JTextField[fieldNames.length];
		for(int i=0;i<fieldNames.length;i++) {
			txtFields[i] = new JTextField(WIDTH);
			JPanel tmp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			tmp.add(new JLabel(fieldNames[i]));
			pnlInputs.add(tmp);
			pnlInputs.add(txtFields[i]);
		}
		addMargin(pnlInputs);
		
		JPanel pnlButton = new JPanel();
		pnlButton.setLayout(new FlowLayout());
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(this);
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(this);
		
		pnlButton.add(btnOk);
		pnlButton.add(btnCancel);
		
		this.setLocationRelativeTo(frame);
		this.add(pnlRadionButtons);
		this.add(pnlInputs);
		this.add(pnlButton);
		this.pack();
		this.setVisible(true);
	}
	
	private void addMargin(JPanel panel) {
		Border current = panel.getBorder();
		Border empty = new EmptyBorder(MARGIN_TOP, MARGIN_LEFT, MARGIN_BOTTOM, MARGIN_RIGHT);
		if (current == null) {
			panel.setBorder(empty);
		} else {
			panel.setBorder(new CompoundBorder(empty, current));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnOk) {
			isOkPressed = true;
			for(int i=0;i<radioButtons.length;i++) {
				if(radioButtons[i].isSelected()) {
					selectedIndex = i;
					break;
				}
			}
			values = new ArrayList<String>();
			for(int i=0;i<txtFields.length;i++) {
				values.add(txtFields[i].getText());
			}
			this.dispose();
		}
		if (ae.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
