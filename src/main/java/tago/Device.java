package tago;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import domain.FindDataCountResult;
import domain.DataResult;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Roberto Canoff
 */
public class Device {

    String API_TAGO = "https://api.tago.io/";
    String URL = API_TAGO + "data";
    String REALTIME_URL = "https://realtime.tago.io/";
    HttpHeaders headers;
    RestTemplate restTemplate;
    public Socket socket;
    String token;

    public Device(String token) {
        this.token = token;
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Device-Token", token);

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(new MappingJackson2HttpMessageConverter());
    }

    public DataResult insert(Data data) {
        HttpEntity<Data> request = new HttpEntity<Data>(data, headers);
        return restTemplate.postForObject(URL, request, DataResult.class);
    }

    public List<Data> find(String key, String type) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam(key, type);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<DataResult> response = restTemplate
                .exchange(builder.build().encode().toUriString(),
                        HttpMethod.GET,
                        entity,
                        DataResult.class);

        return response.getBody().result;
    }

    public Integer count() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam(Constant.Find.QUERY, Constant.Query.COUNT);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<FindDataCountResult> response = restTemplate
                .exchange(builder.build().encode().toUriString(),
                        HttpMethod.GET,
                        entity,
                        FindDataCountResult.class);

        return response.getBody().result;
    }

    public Boolean delete() {
        return deleteDevice(null);
    }

    public Boolean delete(String data_ID) {
        return deleteDevice(data_ID);
    }

    private Boolean deleteDevice(String data_ID) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);

        if (data_ID != null) {
            builder.queryParam("data_ID", data_ID);
        }

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<DataResult> response = restTemplate
                .exchange(builder.build().encode().toUriString(),
                        HttpMethod.DELETE,
                        entity,
                        DataResult.class);

        return response.getBody().status;
    }

    public DataResult update(String id, Data data) {
        return updateDevice(id, data);
    }
    
    public DataResult update(Data data) {
        return updateDevice(null, data);
    }
    
    private DataResult updateDevice(String id, Data data){
        HttpEntity entity = new HttpEntity(data, headers);

        HttpEntity<DataResult> response = restTemplate
                .exchange(URL,
                        HttpMethod.PUT,
                        entity,
                        DataResult.class, id);

        return response.getBody();
    }

    public void listening() {
        if(this.socket == null || !this.socket.connected()){
            try {
                this.socket = IO.socket(REALTIME_URL);
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
}
