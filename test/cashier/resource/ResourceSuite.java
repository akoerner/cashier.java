package cashier.resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({cashier.resource.TransactionTest.class, cashier.resource.UnitTest.class, cashier.resource.TimecardTest.class, cashier.resource.LineTest.class, cashier.resource.UserTest.class, cashier.resource.InvoiceTest.class, cashier.resource.ItemTest.class, cashier.resource.StoreTest.class, cashier.resource.CustomerTest.class, cashier.resource.PersonTest.class, cashier.resource.TicketTest.class, cashier.resource.LocationTest.class, cashier.resource.ShiftTest.class, cashier.resource.EntryTest.class, cashier.resource.TillTest.class})
public class ResourceSuite {

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }
    
}
