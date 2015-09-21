package tago;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import java.util.Date;
/**
 *
 * @author Roberto Canoff
 */
public class Data {

    public String variable;
    public String unit;
    public String value;
    public String type;
    public Date time;
    public Location location;
    
    public String origin;
    public String id;
    public String bucket;
    public String origin_type;
    public String serie;
    public String metadata;

    @JsonIgnore
    @Override
    public String toString() {
        return "Device{" + 
                    "variable=" + variable + 
                    ", unit=" + unit + 
                    ", value=" + value + 
                    ", type=" + type + 
                    ", time=" + time + 
                    ", location=" + location + 
                    ", origin=" + origin + 
                    ", id=" + id + 
                '}';
    }
    
    @JsonIgnore
    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
