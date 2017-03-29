
import domain.Result;
import model.Account;

public class ProfileExample {

    public static void main(String[] args) {
        Account myacc = new Account("7c16da11-2101-4ea3-9568-7249115d73f3");

        Result accInfo = myacc.info();

        Object data = new Object() {
            public String name = "OpenSource Test";
        };

        Result deviceCreate = myacc.device.create(data);

        
        Result profDel = myacc.profileDelete("58790a9b63fdfd0cc1accac9");
    }

}
