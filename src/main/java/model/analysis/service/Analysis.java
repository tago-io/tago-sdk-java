package model.analysis.service;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Config;
import model.device.Device;

public class Analysis {
    private Socket socket;
    private model.analysis.Analysis analysis;
    private String token;
    
    private Config config;

    public Analysis(model.analysis.Analysis analysis, String token) {
        this.analysis = analysis;
        this.token = token;
        config = new Config();
    }

    
    

    public Emitter on(String event, Emitter.Listener listener) {

        if (this.socket == null) {
            try {
                this.socket = IO.socket(config.realtime_url);
            } catch (URISyntaxException ex) {
                Logger.getLogger(Analysis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.socket.on(event, listener);
    }
    
    
    public void listening(String analysisId, final String token) {
        if (this.socket == null || !this.socket.connected()) {
            try {
                this.socket = IO.socket(config.realtime_url);
                socket.connect();

                socket.on("connect", new Emitter.Listener() {

                    @Override
                    public void call(Object... os) {
                        socket.emit("register:analysis", token);
                    }
                });
            } catch (URISyntaxException ex) {
                Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stopListening(String analysisId) {
        if (this.socket != null || this.socket.connected()) {
            this.socket.off(analysisId);
        }
    }
}
