import analysis.Analysis;
import services.Console;
import tago.Listener;

public class NewMain {

    public static void main(String[] args) {
        Analysis myAnalysis = new Analysis();

        Listener listener = new Listener() {
            @Override
            public void call(Object object, Console console) {
                System.out.println("this logs the local console");
                console.log("this logs the tago analysis console");
            }
        };

        myAnalysis.listening(listener, "d43b1695-d8a8-44f5-ae8b-512a7ecffdb9");
    }
}
