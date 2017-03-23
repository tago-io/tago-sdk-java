package model.analysis;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import domain.AnalysisCreateResult;
import domain.AnalysisResult;
import domain.AnalysisTokenResult;
import domain.StringResult;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Config;
import model.device.Device;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Analysis {

    private String token;
    private String api_url;
    private String realtime_url;
    private HttpHeaders headers;
    private RestTemplate restTemplate;
    private Config config;

    public String last_run;
    public String file_name;
    public List<KeyVal> tags;
    public List<KeyVal> variables;
    public String id;
    public String name;
    public String description;
    public String interval;
    public String run_on;
    public Boolean active;
    public String locked_at;
    public String language;
    public Date created_at;
    public Date updated_at;
    public List<String> console;

    public Socket socket;

    public Analysis(String token) {
        loadConfig();
        setToken(token);
    }

    public Analysis() {
        loadConfig();
        setToken(System.getenv("ACCOUNT_TOKEN"));
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        headers.add("Device-Token", token);
    }

    private void loadConfig() {
        config = new Config();
        api_url = config.app_url;
        realtime_url = config.realtime_url;
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(new MappingJackson2HttpMessageConverter());
    }

    public AnalysisResult list() {
        String url = api_url + "/analysis";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<AnalysisResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AnalysisResult.class);

        return response.getBody();
    }

    public AnalysisCreateResult create(Analysis analysis) {
        String url = api_url + "/analysis";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(analysis, headers);

        HttpEntity<AnalysisCreateResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AnalysisCreateResult.class);

        return response.getBody();
    }

    public AnalysisCreateResult edit(Analysis analysis) {
        String url = api_url + "/analysis/" + analysis.id;
        HttpMethod method = HttpMethod.PUT;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(analysis, headers);

        HttpEntity<AnalysisCreateResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AnalysisCreateResult.class);

        return response.getBody();
    }

    public StringResult delete(String analysisId) {
        String url = api_url + "/analysis/" + analysisId;
        HttpMethod method = HttpMethod.DELETE;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<StringResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        StringResult.class);

        return response.getBody();
    }

    public AnalysisCreateResult info(String analysisId) {
        String url = api_url + "/analysis/" + analysisId;
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<AnalysisCreateResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AnalysisCreateResult.class);

        return response.getBody();
    }

    public StringResult run(String analysisId) {
        String url = api_url + "/analysis/" + analysisId + "/run";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<StringResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        StringResult.class);

        return response.getBody();
    }

    public void listening(String analysisId) {
        if (this.socket == null || !this.socket.connected()) {
            try {
                this.socket = IO.socket(realtime_url + "/" + analysisId);
                socket.connect();

                socket.on("connect", new Emitter.Listener() {

                    @Override
                    public void call(Object... os) {
                        socket.emit("register", token);
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
    
     public AnalysisTokenResult tokenGenerate(String analysisId) {
        String url = api_url + "/analysis/" + analysisId + "/token";
        HttpMethod method = HttpMethod.GET;
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<AnalysisTokenResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AnalysisTokenResult.class);

        return response.getBody();
    }
     
     public StringResult uploadScript(String analysisId, AnalysisUpload upload) {
        String url = api_url + "/analysis/" + analysisId + "/upload";
        HttpMethod method = HttpMethod.POST;
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        
        HttpEntity entity = new HttpEntity(upload, headers);

        HttpEntity<StringResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        StringResult.class);

        return response.getBody();
    }
     
     

}
