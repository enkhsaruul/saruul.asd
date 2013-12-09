package edu.mum;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ExceptionTests extends TestCase {

    public static Test suite() {
        final TestSuite suite = new TestSuite();

        suite.addTestSuite(AccountNotFoundExceptionTest.class);
        suite.addTestSuite(CustomerNotFoundExceptionTest.class);
        suite.addTestSuite(DatabaseExceptionTest.class);
        suite.addTestSuite(InternalErrorExceptionTest.class);
        suite.addTestSuite(NotEnoughCreditExceptionTest.class);
        
        return suite;
    }

}
