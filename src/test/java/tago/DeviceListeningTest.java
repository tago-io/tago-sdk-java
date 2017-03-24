package tago;

import com.github.nkzawa.emitter.Emitter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import model.device.Device;
import static org.junit.Assert.*;

public class DeviceListeningTest {
    
    public DeviceListeningTest() {
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
    public void testListening() {
        System.out.println("listening");

        Device device = new Device();

        device.listening();
        device.socket.on("data", new Emitter.Listener() {

            @Override
            public void call(Object... result) {
            }
        });

        assertEquals(true, device.socket.listeners("data").size() > 0);
    }

}
