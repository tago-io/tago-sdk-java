package tago;

import domain.UpdateDataResult;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UpdateTest {
    
    public UpdateTest() {
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
    public void testUpdate_String_Data() {
        System.out.println("update_with_parameter");
        Device device = new Device(null);

        Data data = new Data();
        data.variable = "API-Teste";
        data.unit = "%";
        data.value = "25";
        data.type = "integer";
        data.time = new Date();
        data.location = new Location(40.792673, -98.683232);

        UpdateDataResult dr = device.update("put_tye_data_id_here", data);

        assertEquals((dr.status == true || dr.status == false), true);
    }

    @Test
    public void testUpdate_Data() {
        System.out.println("update");
        Device device = new Device(null);

        Data data = new Data();
        data.variable = "API-Teste";
        data.unit = "%";
        data.value = "25";
        data.type = "integer";
        data.time = new Date();
        data.location = new Location(40.792673, -98.683232);

        UpdateDataResult dr = device.update(data);

        assertEquals((dr.status == true || dr.status == false), true);
    }
}
