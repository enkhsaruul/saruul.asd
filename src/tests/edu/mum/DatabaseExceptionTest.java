package edu.mum;

import junit.framework.TestCase;

import org.junit.Test;

import edu.mum.framework.exception.DatabaseException;

public class DatabaseExceptionTest extends TestCase {
	@Test
	public void testConstructor1 () {
		DatabaseException exception = new DatabaseException();
		
		assertTrue(exception instanceof Exception);
	}
	
	@Test
	public void testConstructor2() {
		DatabaseException exception = new DatabaseException("Message");
		
		assertTrue(exception instanceof Exception);
		assertEquals(exception.getMessage(), "Message");
	}
}
