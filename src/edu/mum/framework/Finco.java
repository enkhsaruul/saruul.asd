package edu.mum.framework;

public class Finco {
	public static void main(String args[]) {
		FincoGUI finco = new FincoGUI("Finco", new String[] {"Street", "City", "State", "Zip", "P/C", "Amount"});
		finco.run();
	}
}
