package example;

import domain.InsertDeviceResult;
import java.util.Date;
import java.util.List;
import tago.Constant;
import tago.Device;
import tago.Init;
import tago.Location;

/**
 *
 * @author Roberto
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Init init = new Init("783c0a20-5ef4-11e5-b9c0-5346110eed9a");

//        Create a variable "Device" to be inserted
        Device device = new Device();
        device.variable = "API-Teste";
        device.unit = "%";
        device.value = "25";
        device.type = "text";
        device.time = new Date();
        device.location = new Location(40.792673, -98.683232);

//        Insert example
        InsertDeviceResult deviceResult = init.insert(device);

//        Find example
        List<Device> devices = init.find(Constant.Find.FILTER, Constant.Filter.TYPE);

//        Count example
        Integer devicesCount = init.count();

//        Delete passing id as parameter
        Boolean deleteWithId = init.delete("55ff4f52916fd80a086af2ef");
//        Delete without parameters (deletes the last inserted device)
        Boolean delete = init.delete();
        
    }

}
