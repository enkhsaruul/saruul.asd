package edu.mum.framework.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class GUIReport extends JDialog implements ActionListener {
	private static final int MARGIN_RIGHT = 2;
	private static final int MARGIN_LEFT = 2;
	private static final int MARGIN_TOP = 5;
	private static final int MARGIN_BOTTOM = 5;
	
	private JButton btnOk;
	private JTextArea area;

	public GUIReport(String frameTitle, JFrame frame, String report) {
		super(frame, true);
		this.setTitle(frameTitle);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		btnOk = new JButton("Ok");
		btnOk.addActionListener(this);
		area = new JTextArea(report);
		area.setEditable(false);
		
		JPanel pnl = new JPanel();
		pnl.add(new JScrollPane(area));
		addMargin(pnl);
		
		this.setLocationRelativeTo(frame);
		this.add(pnl);
		this.add(btnOk);
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
		if(ae.getSource() == btnOk) {
			this.dispose();
		}
	}
}
