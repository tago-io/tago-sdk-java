package model.account;

import domain.StringResult;
import domain.AccountInfoResult;
import domain.AccountProfileResult;
import domain.AccountStatisticsResult;
import domain.CreateTokenResult;
import domain.TokenResult;
import java.util.Date;
import model.TagoModel;
import model.action.Action;
import model.analysis.Analysis;
import model.bucket.Bucket;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Account extends TagoModel{
    public String id;
    public String name;
    public String email;
    public String company;
    public String timezone;
    public Boolean newsletter;
    public Boolean active;
    public String plan;
    public String type;
    public String view_mode;
    public Date last_login;
    public String phone;
    public Long request_limit;
    public Analysis analysis;
    public Action action;
    public Bucket bucket;

    public Account(String token) {
        super(token);
        createClasses(token);
    }

    public Account() {
        super(System.getenv("ACCOUNT_TOKEN"));
        createClasses(token);
    }
    
    private void createClasses(String token){
        analysis = new Analysis(token);
        action = new Action(token);
        bucket = new Bucket(token);
    }

    public AccountInfoResult info() {
        String url = api_url + "/account";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<AccountInfoResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AccountInfoResult.class);

        return response.getBody();
    }

    public AccountStatisticsResult statistics(TagoDate date) {
        String url = api_url + "/statistics";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<AccountStatisticsResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AccountStatisticsResult.class);

        return response.getBody();
    }

    public StringResult edit(Account account) {
        String url = api_url + "/account";
        HttpMethod method = HttpMethod.PUT;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(account, headers);

        HttpEntity<StringResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        StringResult.class);

        return response.getBody();
    }

    public AccountProfileResult profileList() {
        String url = api_url + "/account/profile";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<AccountProfileResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AccountProfileResult.class);

        return response.getBody();
    }

    public StringResult profileCreate(final String mail) {
        String url = api_url + "/account/profile";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        Object body = new Object() {
            public String email = mail;
        };

        HttpEntity entity = new HttpEntity(body, headers);

        HttpEntity<StringResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        StringResult.class);

        return response.getBody();
    }

    public StringResult profileDelete(final String profileId) {
        String url = api_url + "/account/profile/" + profileId;
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

    public TokenResult tokenList() {
        String url = api_url + "/account/profile/token";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<TokenResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        TokenResult.class);

        return response.getBody();
    }

    public CreateTokenResult tokenCreate(Token token) {
        String url = api_url + "/account/profile/token";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(token, headers);

        HttpEntity<CreateTokenResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        CreateTokenResult.class);

        return response.getBody();
    }

    public StringResult tokenDelete() {
        String url = api_url + "/account/profile/token";
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

    public AccountProfileResult login(final String emailParam, final String passwordParam) {
        String url = api_url + "/account/profile/login";
        HttpMethod method = HttpMethod.POST;

        Object body = new Object() {
            public String email = emailParam;
            public String password = passwordParam;
        };

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(body, headers);

        HttpEntity<AccountProfileResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AccountProfileResult.class);

        return response.getBody();
    }

}