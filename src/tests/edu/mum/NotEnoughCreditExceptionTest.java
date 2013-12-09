package edu.mum;

import org.junit.Test;

import edu.mum.framework.exception.NotEnoughCreditException;
import junit.framework.TestCase;

public class NotEnoughCreditExceptionTest extends TestCase {
	@Test
	public void testConstructor1 () {
		NotEnoughCreditException exception = new NotEnoughCreditException();
		
		assertTrue(exception instanceof Exception);
	}
	
	@Test
	public void testConstructor2() {
		NotEnoughCreditException exception = new NotEnoughCreditException("Message");
		
		assertTrue(exception instanceof Exception);
		assertEquals(exception.getMessage(), "Message");
	}
}
