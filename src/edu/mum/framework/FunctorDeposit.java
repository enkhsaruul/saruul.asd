package edu.mum.framework;

public class FunctorDeposit {
	
	double large;
	
	public FunctorDeposit(double large) {
		this.large = large;
	}
	
	int execute(CommandDeposit command) {
		if (command.getAmount() >= large) {
			return TransactionResult.LARGE_AMOUNT;
		}
		return TransactionResult.NORMAL;
	}
}
