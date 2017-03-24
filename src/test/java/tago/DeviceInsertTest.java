package tago;

import com.fasterxml.jackson.core.JsonProcessingException;
import domain.InsertDataResult;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import model.device.Data;
import model.device.Device;
import model.device.Location;
import static org.junit.Assert.assertEquals;

public class DeviceInsertTest {

    public DeviceInsertTest() {
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
    public void testInsert() throws JsonProcessingException {
        System.out.println("insert");
        Device device = new Device();

        Data data = new Data();
        data.variable = "APITeste";
        data.unit = "%";
        data.value = "25";
        data.type = "integer";
        data.time = new Date();
        data.location = new Location(40.792673, -98.683232);

        InsertDataResult idr = device.insert(data);

        assertEquals(idr.status, true);
    }
}
