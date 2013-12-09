package edu.mum;

import org.junit.Test;

import edu.mum.framework.exception.AccountNotFoundException;

import junit.framework.TestCase;

public class AccountNotFoundExceptionTest extends TestCase {
	
	@Test
	public void testConstructor1 () {
		AccountNotFoundException exception = new AccountNotFoundException();
		
		assertTrue(exception instanceof Exception);
	}
	
	@Test
	public void testConstructor2() {
		AccountNotFoundException exception = new AccountNotFoundException("Message");
		
		assertTrue(exception instanceof Exception);
		assertEquals(exception.getMessage(), "Message");
	}
}
