package tago;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

public class DeviceInfo {

    public String name;
    public String description;
    public boolean active;
    public boolean visible;
    public Date created_at;
    public Date updated_at;
    public Date last_access;
    public String id;
    public Bucket bucket;
}
