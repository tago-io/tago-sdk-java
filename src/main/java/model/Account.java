package model;

import domain.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Account extends TagoModel {

    public Device device;
    public Bucket bucket;
    public Action action;
    public Analysis analysis;
    public Dashboard dashboard;

    public Account(String token) {
        super(token);
        device = new Device(token);
        bucket = new Bucket(token);
        action = new Action(token);
        analysis = new Analysis(token);
        dashboard = new Dashboard(token);
    }

    public Result info() {
        String url = api_url + "/account";
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

    public Result statistics(Object date) {
        String url = api_url + "/statistics";
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

    public Result edit(Object account) {
        String url = api_url + "/account";
        HttpMethod method = HttpMethod.PUT;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(account, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result delete() {
        String url = api_url + "/account";
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

    public Result profileList() {
        String url = api_url + "/account/profile";
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

    public Result profileCreate(final String mail) {
        String url = api_url + "/account/profile";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        Object body = new Object() {
            public String email = mail;
        };

        HttpEntity entity = new HttpEntity(body, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result profileDelete(final String profileId) {
        String url = api_url + "/account/profile/" + profileId;
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

    public Result tokenList() {
        String url = api_url + "/account/profile/token";
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

    public Result tokenCreate(Object token) {
        String url = api_url + "/account/profile/token";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(token, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result tokenDelete() {
        String url = api_url + "/account/profile/token";
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

    public Result login(Object data) {
        String url = api_url + "/account/profile/login";
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

}
