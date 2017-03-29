package model;

import domain.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Share extends TagoModel {

    public String id;
    public String email;
    public String permission;
    public String everyone;
    public String name;

    public Share(String token) {
        super(token);
    }

    public Result list(final String paramType, final String paramId) {
        String url = api_url + "/share/type/ref_id";
        HttpMethod method = HttpMethod.POST;

        Object body = new Object() {
            public String type = paramType;
            public String id = paramId;
        };

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(body, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result edit(final String paramType, final String paramId, final Object paramData) {
        String url = api_url + "/share/" + paramId;
        HttpMethod method = HttpMethod.PUT;

        Object body = new Object() {
            public String type = paramType;
            public String id = paramId;
            public Object data = paramData;
        };

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(body, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result invite(final String paramType, final String paramId, final Object paramData) {
        String url = api_url + "/share/type/" + paramId;
        HttpMethod method = HttpMethod.POST;

        Object body = new Object() {
            public String type = paramType;
            public String id = paramId;
            public Object data = paramData;
        };

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(body, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result remove(final String paramType, final String paramId) {
        String url = api_url + "/share/type?id=" + paramId;
        HttpMethod method = HttpMethod.DELETE;

        Object body = new Object() {
            public String type = paramType;
            public String id = paramId;
        };

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(body, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

}
