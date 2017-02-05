package tago;

import domain.FindDataResult;
import java.util.HashMap;
import java.util.Map;
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
    
    @Test
    public void testFind_map_args() {
        System.out.println("find_map_args");
        Device device = new Device(null);
        Map<String, String> params = new HashMap<String, String>();
        params.put("qty", "1000");
        params.put("variable", "apiteste");
        params.put("value", "25");
        params.put("_cache", "false");
        FindDataResult fdr = device.find(params);

        assertEquals(true, fdr.status);
    }   
}
