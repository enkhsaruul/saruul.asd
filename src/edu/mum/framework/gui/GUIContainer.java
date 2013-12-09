package edu.mum.framework.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class GUIContainer extends JFrame {
	
	private static final int MARGIN_RIGHT = 1;
	private static final int MARGIN_LEFT = 1;
	private static final int MARGIN_TOP = 2;
	private static final int MARGIN_BOTTOM = 2;
	
	protected javax.swing.JPanel pnlRightButtons;
	protected javax.swing.JPanel pnlTable;
	protected javax.swing.JPanel pnlTopButtons;

	protected JTable table;
	protected DefaultTableModel tableModel;
	
	public GUIContainer(String frameTitle, String [] columnNames) {
		initComponents();
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel) {
			public boolean isCellEditable(int row, int column){  
			    return false;  
			}
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.pnlTopButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.pnlRightButtons.setLayout(new GridLayout(15, 1));
		this.pnlTable.setLayout(new GridLayout(1, 1));
		
		this.pnlTable.add(new JScrollPane(table));
		this.setVisible(true);
	}
	
	public void addRow(Vector<String> row) {
		tableModel.addRow(row);
	}
	
	/*public int findColumnIndex(String columnName) {
		for(int i=0;i<tableModel.getColumnCount();i++)
			if(tableModel.getColumnName(i).equalsIgnoreCase(columnName))
				return i;
		return -1;
	}*/
	
	public int getSelectedRowIndex() {
		int selection[] = table.getSelectedRows();
		if(selection.length == 0)
			return -1;
		else 
			return selection[0];
	}
	
	public void addButtonAtTop(JButton button) {
		pnlTopButtons.add(button);
	}
	
	public void addButtonAtRight(JButton button) {
		addMargin(button);
		pnlRightButtons.add(button);
	}
	
	private void addMargin(JButton button) {
		Border current = button.getBorder();
		Border empty = new EmptyBorder(MARGIN_TOP, MARGIN_LEFT, MARGIN_BOTTOM, MARGIN_RIGHT);
		if (current == null) {
			button.setBorder(empty);
		} else {
			button.setBorder(new CompoundBorder(empty, current));
		}
	}

	private void initComponents() {

		pnlTopButtons = new javax.swing.JPanel();
		pnlTable = new javax.swing.JPanel();
		pnlRightButtons = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		pnlTopButtons.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Top"));

		javax.swing.GroupLayout pnlTopButtonsLayout = new javax.swing.GroupLayout(
				pnlTopButtons);
		pnlTopButtons.setLayout(pnlTopButtonsLayout);
		pnlTopButtonsLayout.setHorizontalGroup(pnlTopButtonsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 410, Short.MAX_VALUE));
		pnlTopButtonsLayout.setVerticalGroup(pnlTopButtonsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 33, Short.MAX_VALUE));

		pnlTable.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Table"));

		javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(
				pnlTable);
		pnlTable.setLayout(pnlTableLayout);
		pnlTableLayout.setHorizontalGroup(pnlTableLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 280,
				Short.MAX_VALUE));
		pnlTableLayout.setVerticalGroup(pnlTableLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 231,
				Short.MAX_VALUE));

		pnlRightButtons.setBorder(javax.swing.BorderFactory
				.createTitledBorder("Right"));

		javax.swing.GroupLayout pnlRightButtonsLayout = new javax.swing.GroupLayout(
				pnlRightButtons);
		pnlRightButtons.setLayout(pnlRightButtonsLayout);
		pnlRightButtonsLayout.setHorizontalGroup(pnlRightButtonsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 108, Short.MAX_VALUE));
		pnlRightButtonsLayout.setVerticalGroup(pnlRightButtonsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 231, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pnlTopButtons,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(pnlTable,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(pnlRightButtons,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(pnlTopButtons,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														pnlRightButtons,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														pnlTable,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))));

		pack();
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUIContainer("Test", new String[]{"A", "B", "C"}).setVisible(true);
			}
		});
	}

	public void clearTable() {
		while(tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
	}

}
