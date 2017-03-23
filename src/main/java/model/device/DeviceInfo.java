package model.device;

import java.util.Date;
import java.util.List;

public class DeviceInfo {
    public String id;
    public String name;
    public String description;
    public Boolean visible;
    public Boolean active;
    public Bucket bucket;
    public Date last_access;
    public Long request_limit;
    public Date created_at;
    public Date updated_at;
    public List<String> configuration_params;
    
    
    
}
