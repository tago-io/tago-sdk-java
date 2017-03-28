package services;

import domain.Result;
import model.TagoModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Socket extends TagoModel {

    public Socket(String analysisToken) {
        super(analysisToken);
    }

    public Result send(final String BucketId, final String dataEntry) {

        Object data = new Object() {
            public String bucket_id = BucketId;
            public String data = dataEntry;
        };
        String url = api_url + "/analysis/services/socket/send";
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
