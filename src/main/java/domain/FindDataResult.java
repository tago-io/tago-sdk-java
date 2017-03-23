package domain;

import java.util.ArrayList;
import java.util.List;
import tago.Data;

public class FindDataResult extends Result{
    public List<Data> result;
    
    public FindDataResult() {
        result = new ArrayList<>();
    }
    
    
}
