package cashier.net;

import java.net.HttpURLConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConnectionTest {
    
  public ConnectionTest() {
  }

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
   * Test of connect method, of class Connection.
   */
  @Test
  public void testConnect() {
    System.out.println("connect");
    String url = "http://cashier.local/~example";
    String apiKey = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
    String apiSecret = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
    HttpURLConnection expResult = null;
    HttpURLConnection result = Connection.connect(url, apiKey, apiSecret);
    assertEquals(expResult, result);
  }

  /**
   * Test of enable method, of class Connection.
   */
  @Test
  public void testEnable() {
    System.out.println("enable");
    Connection.enable();
  }

  /**
   * Test of disable method, of class Connection.
   */
  @Test
  public void testDisable() {
    System.out.println("disable");
    Connection.disable();
  }
}
