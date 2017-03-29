
import domain.Result;
import model.Device;


public class DeviceExample {


    public static void main(String[] args) {
        Device mydevice = new Device("8aa46f99-3156-4ebd-a275-fdb75c4dccbf");
        
        Object myData = new Object(){
            public String variable = "xx";
            public Integer value = 123;
        };
        
        Result insertResul = mydevice.insert(myData);
        
        Object filter = new Object(){
             public String query = "count";
        };
        
        Result findResult = mydevice.find(filter);
        
        String a = "";
    }
    
}
