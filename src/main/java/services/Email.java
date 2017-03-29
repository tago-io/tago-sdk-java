package services;

import domain.Result;
import model.TagoModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Email extends TagoModel {

    public Email(String analysisToken) {
        super(analysisToken);
    }

    public Result send(Object data) {
        String url = api_url + "/analysis/services/email/send";
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
