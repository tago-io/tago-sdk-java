package model;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import domain.Result;
import java.net.URISyntaxException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Analysis extends TagoModel {

    private Socket socket;
    
    public Analysis(String accountToken) {
        super(accountToken);
    }
    
       
    public Result list() {
        String url = api_url + "/analysis";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result create(Object data) {
        String url = api_url + "/analysis";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(data, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result edit(String analysisId, Object data) {
        String url = api_url + "/analysis/" + analysisId;
        HttpMethod method = HttpMethod.PUT;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(data, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result delete(String analysisId) {
        String url = api_url + "/analysis/" + analysisId;
        HttpMethod method = HttpMethod.DELETE;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

   

    public Result info(String analysisId) {
        String url = api_url + "/analysis/" + analysisId;
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result run(String analysisId, Object scope) {
        String url = api_url + "/analysis/" + analysisId + "/run";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(scope, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result tokenGenerate(String analysisId) {
        String url = api_url + "/analysis/" + analysisId + "/token";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }
    
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

    public Result uploadScript(String analysisId, Object file) {
        String url = api_url + "/analysis/" + analysisId + "/upload";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(file, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }
}
