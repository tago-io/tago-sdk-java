package domain;

import java.util.ArrayList;
import java.util.List;
import tago.Data;

public class FindDataResult {
    public Boolean status;
    public List<Data> result;
    public String message;

    public FindDataResult() {
        result = new ArrayList<>();
    }
    
    
}
