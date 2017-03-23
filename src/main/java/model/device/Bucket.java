package model.device;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.Date;
import java.util.List;

public class Bucket {

    public String name;
    public String id;
    public Long request_limit;
    public Date created_at;
    public Date updated_at;
    public JsonNode meta_variables;
    public List<String> configuration_params;
}


