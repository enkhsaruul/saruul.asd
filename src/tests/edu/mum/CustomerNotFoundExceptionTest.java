package edu.mum;

import org.junit.Test;

import edu.mum.framework.exception.CustomerNotFoundException;
import junit.framework.TestCase;

public class CustomerNotFoundExceptionTest extends TestCase {
	
	@Test
	public void testConstructor1 () {
		CustomerNotFoundException exception = new CustomerNotFoundException();
		
		assertTrue(exception instanceof Exception);
	}
	
	@Test
	public void testConstructor2() {
		CustomerNotFoundException exception = new CustomerNotFoundException("Message");
		
		assertTrue(exception instanceof Exception);
		assertEquals(exception.getMessage(), "Message");
	}
	
}
