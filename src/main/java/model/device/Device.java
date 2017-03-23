package model.device;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import domain.DeleteDataResult;
import domain.DeviceInfoResult;
import domain.FindDataResult;
import domain.InsertDataResult;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Config;
import model.device.Bucket;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tago.Data;

public class Device {

    private String token;
    private String api_url;
    private String realtime_url;
    private HttpHeaders headers;
    private RestTemplate restTemplate;
    private Config config;
    
    public String id;
    public String name;
    public String description;
    public Boolean visible;
    public Boolean active;
    public Bucket bucket;
    public Date last_access;
    public Long request_limit;
    public Date created_at;
    public Date updated_at;
    public List<String> configuration_params;
    
    public Socket socket;


    public Device(String token) {
        loadConfig();
        setToken(token);
    }
    
    public Device(){
        loadConfig();
        setToken(System.getenv("DEVICE_TOKEN"));
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

     public InsertDataResult insert(Data data) throws JsonProcessingException {
         String url = api_url + "/data";
        HttpEntity<Data> request = new HttpEntity<Data>(data, headers);
        return restTemplate.postForObject(url, request, InsertDataResult.class);
    }

    public FindDataResult find(String key, String type) {
        String url = api_url + "/data";
        HttpMethod method = HttpMethod.GET;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(key, type);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<FindDataResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        FindDataResult.class);

        return response.getBody();
    }

    public DeviceInfoResult info() {
        String url = api_url + "/info";
        HttpMethod method = HttpMethod.GET;
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<DeviceInfoResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        DeviceInfoResult.class);

        return response.getBody();
    }
   
    public FindDataResult find(Map<String, String> params) {
        String url = api_url + "/data";
        HttpMethod method = HttpMethod.GET;
                
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entrySet : params.entrySet()) {
            try {
                builder.queryParam(entrySet.getKey(), URLDecoder.decode(entrySet.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<FindDataResult> response = restTemplate.exchange(builder.build().toUri(),
                    method,
                    entity,
                    FindDataResult.class);

        if (response != null) {
            return response.getBody();
        }

        return null;

    }

//    public FindDataCountResult count() {
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(api_url)
//                .queryParam(Constant.Find.QUERY, Constant.Query.COUNT);
//
//        HttpEntity entity = new HttpEntity(headers);
//
//        HttpEntity<FindDataCountResult> response = restTemplate
//                .exchange(builder.build().toUriString(),
//                        HttpMethod.GET,
//                        entity,
//                        FindDataCountResult.class);
//
//        return response.getBody();
//    }

    public DeleteDataResult remove() {
        return deleteDevice(null);
    }

    public DeleteDataResult remove(String data_ID) {
        return deleteDevice(data_ID);
    }

    private DeleteDataResult deleteDevice(String data_ID) {
        String url = api_url + "/data";
        HttpMethod method = HttpMethod.DELETE;
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        if (data_ID != null) {
            builder.queryParam("data_ID", data_ID);
        }

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<DeleteDataResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        DeleteDataResult.class);

        return response.getBody();
    }

    public void listening() {
        if (this.socket == null || !this.socket.connected()) {
            try {
                this.socket = IO.socket(realtime_url + "/data");
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
    
    public void stopListening() {
        if (this.socket != null || this.socket.connected()) {
            this.socket.off("data");
        }
    }
}
