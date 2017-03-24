package model.bucket;

import com.fasterxml.jackson.databind.JsonNode;
import domain.BucketCreateResult;
import domain.BucketInfoResult;
import domain.BucketListResult;
import domain.Result;
import domain.StringResult;
import java.util.List;
import model.Share;
import model.TagoModel;
import model.analysis.KeyVal;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Bucket extends TagoModel {

    public String name;
    public String description;
    public String id;
    public Long request_limit;
    public JsonNode meta_variables;
    public List<String> configuration_params;
    public List<KeyVal> tags;
    public String account;
    public Boolean visible;
    public Boolean backup;
    public String data_retention;
    public String last_backup;
    public String last_retention;

    public Bucket(String token) {
        super(token);
    }

    public Bucket() {
        super(System.getenv("ACCOUNT_TOKEN"));
    }

    public BucketListResult list() {
        String url = api_url + "/bucket";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<BucketListResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        BucketListResult.class);

        return response.getBody();
    }

    public BucketCreateResult create() {
        String url = api_url + "/bucket";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(Bucket.this, headers);

        HttpEntity<BucketCreateResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        BucketCreateResult.class);

        id = response.getBody().result.bucket;
        return response.getBody();
    }

    public StringResult edit() {
        return editBucket(id);
    }

    public StringResult edit(String id) {
        return editBucket(id);
    }

    private StringResult editBucket(String id) {
        String url = api_url + "/bucket/" + id;
        HttpMethod method = HttpMethod.PUT;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(Bucket.this, headers);

        HttpEntity<StringResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        StringResult.class);

        return response.getBody();
    }

    public StringResult delete() {
        return deleteBucket(id);
    }

    public StringResult delete(String id) {
        return deleteBucket(id);
    }

    private StringResult deleteBucket(String id) {
        String url = api_url + "/bucket/" + id;
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

    public BucketInfoResult info() {
        return infoBucket(id);
    }

    public BucketInfoResult info(String id) {
        return infoBucket(id);
    }

    private BucketInfoResult infoBucket(String id) {
        String url = api_url + "/bucket/" + id;
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<BucketInfoResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        BucketInfoResult.class);

        return response.getBody();
    }

    private BucketInfoResult backupInfo(String id) {
        String url = api_url + "/backup/" + id;
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<BucketInfoResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        BucketInfoResult.class);

        return response.getBody();
    }

    public BucketInfoResult backupList(String id) {
        String url = api_url + "/backup";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<BucketInfoResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        BucketInfoResult.class);

        return response.getBody();
    }

    public StringResult backupDelete() {
        return deleteBackup(id);
    }

    public StringResult backupDelete(String id) {
        return deleteBackup(id);
    }

    private StringResult deleteBackup(String id) {
        String url = api_url + "/backup/" + id;
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

    public BucketInfoResult backupRecover() {
        String url = api_url + "/backup/recover";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(Bucket.this, headers);

        HttpEntity<BucketInfoResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        BucketInfoResult.class);

        return response.getBody();
    }

    public Result shareList() {
        return bucketShareList(id);
    }

    public Result shareList(String id) {
        return bucketShareList(id);
    }

    private Result bucketShareList(String id) {
        String url = api_url + "/share/type/ref_id";
        Share share = new Share(token);

        return share.list("bucket", id);
    }

    public Result shareSendInvite(String id, Share data) {
        String url = api_url + "/share/type/ref_id";
        Share share = new Share(token);

        return share.invite("bucket", id, data);
    }

    public Result shareEdit(String id, Share data) {
        String url = api_url + "/share/type/ref_id";
        Share share = new Share(token);

        return share.edit("bucket", id, data);
    }

    public Result shareDelete(String id) {
        String url = api_url + "/share/type/ref_id";
        Share share = new Share(token);

        return share.remove("bucket", id);
    }
    
    
    public Result exportData(final String paramOutput,final  List<Bucket> paramBuckets) {
        String url = api_url + "/data/export?output=" + paramOutput;
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        Object body = new Object(){
            public String output = paramOutput;
            public List<Bucket> buckets = paramBuckets;
        };
        
        HttpEntity entity = new HttpEntity(body, headers);

        HttpEntity<Result> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        Result.class);

        return response.getBody();
    }

}
