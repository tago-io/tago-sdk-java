package tago;

import domain.DeleteDataResult;
import domain.FindDataCountResult;
import domain.FindDataResult;
import domain.InsertDataResult;
import java.util.List;
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
    HttpHeaders headers;
    RestTemplate restTemplate;

    public Device(String token) {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Device-Token", token);

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(new MappingJackson2HttpMessageConverter());
    }

    public InsertDataResult insert(Data data) {
        HttpEntity<Data> request = new HttpEntity<Data>(data, headers);
        return restTemplate.postForObject(URL, request, InsertDataResult.class);
    }

    public List<Data> find(String key, String type) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam(key, type);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<FindDataResult> response = restTemplate
                .exchange(builder.build().encode().toUriString(),
                        HttpMethod.GET,
                        entity,
                        FindDataResult.class);

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

        HttpEntity<DeleteDataResult> response = restTemplate
                .exchange(builder.build().encode().toUriString(),
                        HttpMethod.DELETE,
                        entity,
                        DeleteDataResult.class);

        return response.getBody().status;
    }

    public InsertDataResult update(String id, Data data) {
        return updateDevice(id, data);
    }
    
    public InsertDataResult update(Data data) {
        return updateDevice(null, data);
    }
    
    private InsertDataResult updateDevice(String id, Data data){
        HttpEntity entity = new HttpEntity(data, headers);

        HttpEntity<InsertDataResult> response = restTemplate
                .exchange(URL,
                        HttpMethod.PUT,
                        entity,
                        InsertDataResult.class, id);

        return response.getBody();
    }

    public InsertDataResult listening(Data data) throws Exception {
        throw new Exception("Not Implemented yet");
    }
}
