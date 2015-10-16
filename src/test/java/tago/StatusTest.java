package tago;

import com.google.gson.Gson;
import domain.StatusResult;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatusTest {
    
    public StatusTest() {
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
    public void testInfo() {
        System.out.println("status");
        Device device = new Device(null);
        StatusResult dir = device.status();
        System.out.println(new Gson().toJson(dir.result));
        assertEquals(true, dir.status);
    }   
}
