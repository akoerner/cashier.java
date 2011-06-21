package cashier.net;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ResourceTest {
    
  public ResourceTest() {
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

  /**
   * Test of getResource method, of class Resource.
   */
  @Test
  public void testGetResource() {
    System.out.println("getResource");
    Resource instance = new ResourceImpl();
    String expResult = "";
    String result = instance.getResource();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setTable method, of class Resource.
   */
  @Test
  public void testSetTable() {
    System.out.println("setTable");
    String table = "";
    Resource instance = new ResourceImpl();
    instance.setTable(table);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setResource method, of class Resource.
   */
  @Test
  public void testSetResource() {
    System.out.println("setResource");
    String resource = "";
    Resource instance = new ResourceImpl();
    instance.setResource(resource);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of save method, of class Resource.
   */
  @Test
  public void testSave() {
    System.out.println("save");
    Resource instance = new ResourceImpl();
    instance.save();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of destroy method, of class Resource.
   */
  @Test
  public void testDestroy_0args() {
    System.out.println("destroy");
    Resource instance = new ResourceImpl();
    instance.destroy();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of find method, of class Resource.
   */
  @Test
  public void testFind() {
    System.out.println("find");
    int id = 0;
    Resource expResult = null;
    Resource result = Resource.find(id);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of all method, of class Resource.
   */
  @Test
  public void testAll_int_int() {
    System.out.println("all");
    int limit = 0;
    int offset = 0;
    Resource.all(limit, offset);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of all method, of class Resource.
   */
  @Test
  public void testAll_0args() {
    System.out.println("all");
    Resource.all();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of destroy method, of class Resource.
   */
  @Test
  public void testDestroy_Integer() {
    System.out.println("destroy");
    Integer id = null;
    Resource.destroy(id);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of fromJSON method, of class Resource.
   */
  @Test
  public void testFromJSON() {
    System.out.println("fromJSON");
    String json = "";
    Resource expResult = null;
    Resource result = Resource.fromJSON(json);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of toJson method, of class Resource.
   */
  @Test
  public void testToJson() {
    System.out.println("toJson");
    Resource resource = null;
    String expResult = "";
    String result = Resource.toJson(resource);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  public class ResourceImpl extends Resource {
  }
}
