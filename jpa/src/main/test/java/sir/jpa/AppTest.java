package sir.jpa;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test 
 */
public class AppTest 
    extends TestCase
{
  
    
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite des test
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * 
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
