package services;

import domain.Result;
import model.TagoModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Mqtt extends TagoModel {

    public Mqtt(String analysisToken) {
        super(analysisToken);
    }

    public Result publish(Object data) {
        String url = api_url + "/analysis/services/mqtt/publish";
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
