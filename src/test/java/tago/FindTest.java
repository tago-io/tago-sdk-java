package tago;

import domain.FindDataResult;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FindTest {
    
    public FindTest() {
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
    public void testFind() {
        System.out.println("find");
        Device device = new Device(null);
        FindDataResult fdr = device.find(Constant.Find.FILTER, Constant.Filter.TYPE);

        assertEquals(true, fdr.status);
    }   
}
