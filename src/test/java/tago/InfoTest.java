package tago;

import domain.DeviceInfoResult;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InfoTest {
    
    public InfoTest() {
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
        System.out.println("Info");
        Device device = new Device(null);
        DeviceInfoResult dir = device.info();

        assertEquals(true, dir.status);
    }   
}
