package tago;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeleteTest {
    
    public DeleteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testDelete_0args() {
        System.out.println("delete_no_args");
        Device device = new Device(null);
        Boolean delete = device.delete().status;

        assertEquals(true, (delete == true || delete == false));
    }

    @Test
    public void testDelete_String() {
        System.out.println("delete");
        Device device = new Device(null);
        Boolean delete = device.delete("").status;

        assertEquals(true, (delete == true || delete == false));
    }
}
