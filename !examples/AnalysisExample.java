
import analysis.Analysis;
import com.github.nkzawa.emitter.Emitter;


public class AnalysisExample {
    public static void main(String[] args) {
        Analysis myanalysis = new Analysis();

        Emitter.Listener listener = new Emitter.Listener() {
            @Override
            public void call(Object... context) {
                //Do anything you want here
            }
        };

        myanalysis.listening(listener, "d43b1695-d8a8-44f5-ae8b-512a7ecffdb9");
    }
}
