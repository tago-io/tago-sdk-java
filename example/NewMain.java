

import com.github.nkzawa.emitter.Emitter;
import domain.FindDataCountResult;
import domain.InsertDataResult;
import java.util.Date;
import java.util.List;
import tago.Constant;
import tago.Data;
import tago.Device;
import tago.Location;

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
        InsertDataResult idr = device.insert(data);
        
//        Update example
        device.update("put_tye_data_id_here", data);
        
//        Updates the last record
        device.update(data);

//        Find example
        List<Data> dataList = device.find(Constant.Find.FILTER, Constant.Filter.TYPE).result;

//        Count example
        FindDataCountResult dataCount = device.count();
        Integer count = dataCount.result;

//        Delete passing id as parameter
        Boolean deleteWithId = device.delete("put_the_data_id_here").status;
        
//        Delete without parameters (deletes the last inserted device)
        Boolean delete = device.delete().status;
        
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
