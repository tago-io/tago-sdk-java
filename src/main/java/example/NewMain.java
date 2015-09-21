package example;

import domain.InsertDataResult;
import java.util.Date;
import java.util.List;
import tago.Constant;
import tago.Data;
import tago.Device;
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
        
        Device device = new Device("783c0a20-5ef4-11e5-b9c0-5346110eed9a");

//        Create a variable "data" to be inserted
        Data data = new Data();
        data.variable = "API-Teste";
        data.unit = "%";
        data.value = "25";
        data.type = "text";
        data.time = new Date();
        data.location = new Location(40.792673, -98.683232);

//        Insert example
        InsertDataResult dataResult = device.insert(data);

//        Find example
        List<Data> dataList = device.find(Constant.Find.FILTER, Constant.Filter.TYPE);

//        Count example
        Integer dataCount = device.count();

//        Delete passing id as parameter
        Boolean deleteWithId = device.delete("55ff4f52916fd80a086af2ef");
        
//        Delete without parameters (deletes the last inserted device)
        Boolean delete = device.delete();
        
    }

}
