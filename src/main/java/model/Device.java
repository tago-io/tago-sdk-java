package model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.nkzawa.socketio.client.Socket;
import domain.Result;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Device extends TagoModel {

    public Socket socket;

    public Device(String token) {
        super(token);
    }

    public Result list() {
        String url = api_url + "/device";
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
        String url = api_url + "/device";
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

    public Result edit(String deviceId, Object data) {
        String url = api_url + "/device/" + deviceId;
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

    public Result info(String deviceId) {
        String url;
        if (deviceId == null) {
            url = api_url + "/device";
        } else {
            url = api_url + "/device/" + deviceId;
        }
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

    public Result insert(Object data) {
        String url = api_url + "/data";
        HttpEntity<Object> request = new HttpEntity<Object>(data, headers);
        return restTemplate.postForObject(url, request, Result.class);
    }

    public Result delete(String deviceId) {
        String url = api_url + "/device/" + deviceId;
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

    public Result tokenList(String deviceId) {
        String url = api_url + "/device/token/" + deviceId;
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

    public Result tokenCreate(String deviceId, Object paramData) {
        String url = api_url + "/device/token";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = mapper.convertValue(paramData, JsonNode.class);

        ((ObjectNode) data).put("device", deviceId);

        HttpEntity entity = new HttpEntity(data, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result tokenDelete(String tokenId) {
        String url = api_url + "/device/token/" + tokenId;
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

    public Result find(Object filter) {
        String url = api_url + "/data";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(filter, headers);
        HttpEntity<Result> response = restTemplate.exchange(builder.build().toUri(),
                method,
                entity,
                Result.class);

        if (response != null) {
            return response.getBody();
        }

        return null;

    }

    public Result remove(String data_ID, final Integer paramQty) {
        String url = api_url + "/data";
        HttpMethod method = HttpMethod.DELETE;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        if (data_ID != null) {
            builder.queryParam("data_ID", data_ID);
        }

        Object body;
        if (paramQty == null) {
            body = new Object() {
                public Integer qty = 1;
            };
        } else {
            body = new Object() {
                public Integer qty = paramQty;
            };
        }

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }
}
