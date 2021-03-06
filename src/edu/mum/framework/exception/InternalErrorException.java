package edu.mum.framework.exception;

public class InternalErrorException extends RuntimeException {

	public InternalErrorException() {
		super();
	}

	public InternalErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public InternalErrorException(String message) {
		super(message);
	}

	public InternalErrorException(Throwable cause) {
		super(cause);
	}
	
}
