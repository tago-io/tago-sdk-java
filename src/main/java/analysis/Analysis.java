package analysis;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import java.net.URISyntaxException;
import model.TagoModel;

public class Analysis extends TagoModel{

    model.Analysis analysis;
    public Analysis() {
        super("");
    }
    
    
    public Analysis(model.Analysis analysis, String token) {
        super(token);
        this.analysis = analysis;
    }
        
    private Socket socket;
    
    public void listening(Emitter.Listener listener, final String token) {
        if (this.socket == null || !this.socket.connected()) {
            try {
                this.socket = IO.socket(config.realtime_url);
                
                socket.on("run:analysis", listener);

                socket.on("connect", new Emitter.Listener() {

                    @Override
                    public void call(Object... os) {
                        socket.emit("register:analysis", token);
                    }
                });
                
                socket.connect();
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void stopListening(String analysisId) {
        if (this.socket != null || this.socket.connected()) {
            this.socket.off(analysisId);
        }
    }
}
