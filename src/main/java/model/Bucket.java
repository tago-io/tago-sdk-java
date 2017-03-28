package model;

import domain.Result;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Bucket extends TagoModel {

    public Bucket(String accountToken) {
        super(accountToken);
    }

    public Result list(Object devices) {
        String url = api_url + "/bucket";
        if (devices != null) {
            url += "?devices=true";
        }
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(devices, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result create(Object data) {
        String url = api_url + "/bucket";
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

    public Result edit(String bucketId, Object data) {
        String url = api_url + "/bucket/" + bucketId;
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

    public Result delete(String bucketId) {
        String url = api_url + "/bucket/" + bucketId;
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

    public Result info(String bucketId) {
        String url = api_url + "/bucket/" + bucketId;
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

    public Result backupInfo(String backupId) {
        String url = api_url + "/backup/" + backupId;
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

    public Result backupList() {
        String url = api_url + "/backup";
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

    public Result backupDelete(String backupId) {
        String url = api_url + "/backup/" + backupId;
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

    public Result backupRecover(Object data) {
        String url = api_url + "/backup/recover";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(Bucket.this, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

    public Result shareList(String bucketId) {
        Share share = new Share(token);

        return share.list("bucket", bucketId);
    }

    public Result shareSendInvite(String bucketId, Object data) {
        Share share = new Share(token);

        return share.invite("bucket", bucketId, data);
    }

    public Result shareEdit(String shareId, Object data) {
        Share share = new Share(token);

        return share.edit("bucket", shareId, data);
    }

    public Result shareDelete(String shareId) {
        Share share = new Share(token);

        return share.remove("bucket", shareId);
    }
    
    
    public Result exportData(final String paramOutput, final List<Object> buckets) {
        String url = api_url + "/data/export?output=" + paramOutput;
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        
        HttpEntity entity = new HttpEntity(buckets, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

}
