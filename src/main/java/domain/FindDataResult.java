package domain;

import java.util.ArrayList;
import java.util.List;
import model.device.Data;

public class FindDataResult extends Result{
    public List<Data> result;
    
    public FindDataResult() {
        result = new ArrayList<>();
    }
    
    
}
