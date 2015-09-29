package example;

import com.github.nkzawa.emitter.Emitter;
import domain.DataResult;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tago.Constant;
import tago.Data;
import tago.Device;
import tago.Location;

/**
 *
 * @author Roberto Canoff
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Device device = new Device("put_the_device_token_here");

//        Create a variable "data" to be inserted or updated
        Data data = new Data();
        data.variable = "API-Teste";
        data.unit = "%";
        data.value = "25";
        data.type = "integer";
        data.time = new Date();
        data.location = new Location(40.792673, -98.683232);

//        Insert example
        DataResult dataResult = device.insert(data);
        
//        Update example
        device.update("put_tye_data_id_here", data);
        
//        Updates the last record
        device.update(data);

//        Find example
        List<Data> dataList = device.find(Constant.Find.FILTER, Constant.Filter.TYPE);

//        Count example
        Integer dataCount = device.count();

//        Delete passing id as parameter
        Boolean deleteWithId = device.delete("put_the_data_id_here");
        
//        Delete without parameters (deletes the last inserted device)
        Boolean delete = device.delete();
        
//        Listening example
        device.listening();
        device.socket.on("data", new Emitter.Listener() {

            @Override
            public void call(Object... result) {
//                The method call will run when new data is inserted at the api
//                the result will be the object "result"
            }
        });
    }
}
