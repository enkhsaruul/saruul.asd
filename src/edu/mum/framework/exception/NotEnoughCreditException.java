package edu.mum.framework.exception;

public class NotEnoughCreditException extends RuntimeException {

	public NotEnoughCreditException() {
		super();
	}

	public NotEnoughCreditException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEnoughCreditException(String message) {
		super(message);
	}

	public NotEnoughCreditException(Throwable cause) {
		super(cause);
	}
	
}
