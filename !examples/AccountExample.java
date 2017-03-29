
import com.github.nkzawa.emitter.Emitter;
import domain.Result;
import model.Account;

public class AccountExample {

    public static void main(String[] args) {
        Account myacc = new Account("7c16da11-2101-4ea3-9568-7249115d73f3");

        Result accinfo = myacc.info();


        
        Object data = new Object() {
            public String name = "OpenSource Test";
        };
        
        Result deviceCreate = myacc.device.create(data);
        
        
        Emitter.Listener listener = new Emitter.Listener() {
            @Override
            public void call(Object... info) {
                System.out.println(info);
            }
        };
        
        myacc.dashboard.listening(listener, "556388ea791aa76a07f0ba43");
    }

}
