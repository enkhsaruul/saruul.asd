package edu.mum;

import org.junit.Test;

import edu.mum.framework.exception.InternalErrorException;
import junit.framework.TestCase;

public class InternalErrorExceptionTest extends TestCase {
	@Test
	public void testConstructor1 () {
		InternalErrorException exception = new InternalErrorException();
		
		assertTrue(exception instanceof Exception);
	}
	
	@Test
	public void testConstructor2() {
		InternalErrorException exception = new InternalErrorException("Message");
		
		assertTrue(exception instanceof Exception);
		assertEquals(exception.getMessage(), "Message");
	}
}
