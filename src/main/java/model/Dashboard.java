package model;

import domain.Result;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Dashboard extends TagoModel {
    
    public Dashboard(String accountToken) {
        super(accountToken);
    }

    public Result list() {
        String url = api_url + "/dashboard";
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
        String url = api_url + "/dashboard";
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

    
    public Result edit(String dashboardId, Object data) {
        String url = api_url + "/dashboard/" + dashboardId;
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

    public Result delete(String dashboardId) {
        String url = api_url + "/dashboard/" + dashboardId;
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

    public Result info(String dashboardId) {
        String url = api_url + "/dashboard/" + dashboardId;
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
    
    public Result shareList(String dashboardId) {
        Share share = new Share(token);

        return share.list("dashboard", dashboardId);
    }

    public Result shareSendInvite(String dashboardId, Object data) {
        Share share = new Share(token);

        return share.invite("dashboard", dashboardId, data);
    }

    public Result shareEdit(String shareId, Object data) {
        Share share = new Share(token);

        return share.edit("dashboard", shareId, data);
    }

    public Result shareDelete(String shareId) {
        Share share = new Share(token);

        return share.remove("dashboard", shareId);
    }
    
    public Result genPublicToken(String dashboardId) {
        String url = api_url + "/dashboard/" + dashboardId + "/share/public";
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
    
    public Result shareClone(String dashboardId, Object data) {
        String url = api_url + "/dashboard/" + dashboardId + "/share/copy";
        HttpMethod method = HttpMethod.GET;

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
