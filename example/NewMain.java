

import com.github.nkzawa.emitter.Emitter;
import domain.AnalysisTokenResult;
import domain.InsertDataResult;
import java.util.Date;
import java.util.List;
import model.account.Account;
import model.analysis.service.Analysis;
import model.device.Device;
import tago.Constant;
import tago.Data;
import tago.Location;

public class NewMain {

    
    public static void dataExample(){
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
        
       
//        Find example
        List<Data> dataList = device.find(Constant.Find.FILTER, Constant.Filter.TYPE).result;


//        Delete passing id as parameter
        Boolean deleteWithId = device.remove("put_the_data_id_here").status;
        
//        Delete without parameters (deletes the last inserted device)
        Boolean delete = device.remove().status;
        
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
    
    public static void analysisExample(){
        final Account account = new Account("account token");       
        account.analysis.name = "test java sdk";
        account.analysis.run_on = "external";
        
        //Creates a new analysis
        account.analysis.create();
        AnalysisTokenResult atr = account.analysis.tokenGenerate();
        
        
        Analysis analysis = new Analysis(account.analysis, account.token);
        // Creates a listener for the registration
        analysis.on("register:analysis", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                System.out.println(os);
//                runs the analysis whene listener is registered
                account.analysis.run(account.analysis.id);
            }
        });
        
        // Creates a listener for the analysis
        analysis.on("run:analysis", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                System.out.println(os);
            }
        });
        
        // Starts listening for the analysis
        analysis.listening(account.analysis.id, atr.result.analysis_token);
    }
    
}
