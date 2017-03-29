package services;

import domain.Result;
import java.util.Date;
import model.TagoModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Console extends TagoModel {

    public Console(String analysisToken) {
        super(analysisToken);
    }

    public Result log(final String paramMessage, final Long paramTimestamp) {
        final Long tempTimestamp = new Date().getTime();

        Object data = new Object() {
            public String message = paramMessage;
            public Long timestamp
                    = (paramTimestamp != null ? paramTimestamp : tempTimestamp);
        };
        String url = api_url + "/analysis/services/console/send";
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
