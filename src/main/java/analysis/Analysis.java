package analysis;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import java.net.URISyntaxException;
import java.util.Date;
import model.TagoModel;
import org.json.JSONArray;
import services.Console;
import services.Email;
import services.Sms;
import tago.Listener;

public class Analysis extends TagoModel {

    public Sms sms;
    public Email email;
    public services.Socket socket;
    public Console console;

    model.Analysis analysis;

    public Analysis() {
        super("");
        initClasses(token);
    }

    public Analysis(String token) {
        super(token);
        initClasses(token);
    }

    public Analysis(model.Analysis analysis, String token) {
        super(token);
        this.analysis = analysis;
        initClasses(token);
    }

    private void initClasses(String token) {
        sms = new Sms(token);
        email = new Email(token);
        socket = new services.Socket(token);
        console = new Console(token);
    }

    private Socket socketIo;

    public void listening(final Listener listener, final String token) {
        if (this.socketIo == null || !this.socketIo.connected()) {
            try {
                this.socketIo = IO.socket(config.realtime_url);

                socketIo.on("run:analysis", new Emitter.Listener() {
                    @Override
                    public void call(Object... os) {
                        try {
                            ObjectMapper mapper = new ObjectMapper();
                            JSONArray data = mapper.convertValue(os[0], JSONArray.class);
                            Console console = new Console(token);
                            for (int i = 0; i < data.length(); i++) {

                                console.log(data.getJSONObject(i).toString(), new Date().getTime());
                                System.out.println(data.getJSONObject(i).toString());
                            }

                        } catch (Exception e) {
                        }
                        if (listener != null) {
                            listener.call(os);
                        }
                    }
                });

                socketIo.on("connect", new Emitter.Listener() {

                    @Override
                    public void call(Object... os) {
                        socketIo.emit("register:analysis", token);
                    }
                });

                socketIo.connect();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void stopListening(String analysisId) {
        if (this.socketIo != null || this.socketIo.connected()) {
            this.socketIo.off(analysisId);
        }
    }
}
