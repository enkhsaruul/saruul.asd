package edu.mum.framework;

public class FunctorWithdraw {
	
	double large;
	
	public FunctorWithdraw(double large) {
		this.large = large;
	}
	
	int execute(CommandWithdraw command) {
		if (command.getAccount().getCurrentBalance() < command.getAmount())
			return TransactionResult.NOT_ENOUGH_CREDIT;
		else if (command.getAmount() >= large)
			return TransactionResult.LARGE_AMOUNT;
		return TransactionResult.NORMAL;
	}
}
