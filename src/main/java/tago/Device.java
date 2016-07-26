package tago;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import domain.FindDataCountResult;
import domain.DeleteDataResult;
import domain.DeviceInfoResult;
import domain.FindDataResult;
import domain.InsertDataResult;
import domain.StatusResult;
import domain.UpdateDataResult;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Device {

    String API_TAGO;
    String URL_DATA;
    String URL_INFO;
    String URL_STATUS;
    String REALTIME_URL;
    HttpHeaders headers;
    RestTemplate restTemplate;
    public Socket socket;
    String DEVICE_TOKEN;

    public Device(String token) {
        loadVariables(token);
        setTemplateHeaders();
    }

    private void setTemplateHeaders() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Device-Token", DEVICE_TOKEN);

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(new MappingJackson2HttpMessageConverter());
    }

    private void loadVariables(String token) {
        this.DEVICE_TOKEN = token;
        if (token == null) {
            this.DEVICE_TOKEN = System.getenv("TAGO_TOKEN_DEVICE");
        }

        this.API_TAGO = System.getenv("TAGO_SERVER");
        if (this.API_TAGO == null) {
            this.API_TAGO = "https://api.tago.io/";
        }

        URL_DATA = API_TAGO + "data";
        URL_INFO = API_TAGO + "info";
        URL_STATUS = API_TAGO + "status";
        
        this.REALTIME_URL = System.getenv("TAGO_REALTIME");
        if (this.REALTIME_URL == null) {
            this.REALTIME_URL = "wss://realtime.tago.io/";
        }
    }

    public InsertDataResult insert(Data data) {
        HttpEntity<Data> request = new HttpEntity<Data>(data, headers);
        return restTemplate.postForObject(URL_DATA, request, InsertDataResult.class);
    }

    public FindDataResult find(String key, String type) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_DATA)
                .queryParam(key, type);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<FindDataResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        HttpMethod.GET,
                        entity,
                        FindDataResult.class);

        return response.getBody();
    }

    public DeviceInfoResult info() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_INFO);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<DeviceInfoResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        HttpMethod.GET,
                        entity,
                        DeviceInfoResult.class);

        return response.getBody();
    }
   
    public StatusResult status() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_STATUS);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<StatusResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        HttpMethod.GET,
                        entity,
                        StatusResult.class);

        return response.getBody();
    }

    public FindDataResult find(Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_DATA);
        for (Map.Entry<String, String> entrySet : params.entrySet()) {
            try {
                builder.queryParam(entrySet.getKey(), URLDecoder.decode(entrySet.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        HttpEntity entity = new HttpEntity(headers);
        HttpEntity<FindDataResult> response = restTemplate.exchange(builder.build().toUri(),
                    HttpMethod.GET,
                    entity,
                    FindDataResult.class);

        if (response != null) {
            return response.getBody();
        }

        return null;

    }

    public FindDataCountResult count() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_DATA)
                .queryParam(Constant.Find.QUERY, Constant.Query.COUNT);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<FindDataCountResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        HttpMethod.GET,
                        entity,
                        FindDataCountResult.class);

        return response.getBody();
    }

    public DeleteDataResult delete() {
        return deleteDevice(null);
    }

    public DeleteDataResult delete(String data_ID) {
        return deleteDevice(data_ID);
    }

    private DeleteDataResult deleteDevice(String data_ID) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_DATA);

        if (data_ID != null) {
            builder.queryParam("data_ID", data_ID);
        }

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<DeleteDataResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        HttpMethod.DELETE,
                        entity,
                        DeleteDataResult.class);

        return response.getBody();
    }

    public UpdateDataResult update(String id, Data data) {
        return updateDevice(id, data);
    }

    public UpdateDataResult update(Data data) {
        return updateDevice(null, data);
    }

    private UpdateDataResult updateDevice(String id, Data data) {
        HttpEntity entity = new HttpEntity(data, headers);

        HttpEntity<UpdateDataResult> response = restTemplate
                .exchange(URL_DATA,
                        HttpMethod.PUT,
                        entity,
                        UpdateDataResult.class, id);

        return response.getBody();
    }

    public void listening() {
        if (this.socket == null || !this.socket.connected()) {
            try {
                this.socket = IO.socket(REALTIME_URL);
                socket.connect();

                socket.on("connect", new Emitter.Listener() {

                    @Override
                    public void call(Object... os) {
                        socket.emit("register", DEVICE_TOKEN);
                    }
                });
            } catch (URISyntaxException ex) {
                Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
