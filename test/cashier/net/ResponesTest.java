package cashier.net;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ResourceTest.ResourceImpl;
import static org.junit.Assert.*;

public class ResponesTest {
	public ResponseTest (){}
	
	  @BeforeClass
	  public static void setUpClass() throws Exception {
	  }

	  @AfterClass
	  public static void tearDownClass() throws Exception {
	  }
	  
	  @Before
	  public void setUp() {
	  }
	  
	  @After
	  public void tearDown() {
	  }
	
	  /**
	   * Test of getTable method, of class Resource.
	   */
	  @Test
	  public void testGetTable() {
	    System.out.println("getTable");
	    Resource instance = new ResourceImpl();
	    String expResult = "";
	    String result = instance.getTable();
	    assertEquals(expResult, result);
	    // TODO review the generated test code and remove the default call to fail.
	    fail("The test case is a prototype.");
	  }
}
