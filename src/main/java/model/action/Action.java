package model.action;

import domain.ActionCreateResult;
import domain.ActionInfoResult;
import domain.ActionListResult;
import domain.StringResult;
import java.util.List;
import model.TagoModel;
import model.analysis.KeyVal;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Action extends TagoModel {
    
    public String last_run;
    public List<KeyVal> tags;
    public String id;
    public String account;
    public String name;
    public Boolean lock;
    public Boolean active;
    public ActionInfo action;
    public String description;

    public Action(String token) {
        super(token);
    }

    public Action() {
        super(System.getenv("ACCOUNT_TOKEN"));
    }

    public ActionListResult list() {
        String url = api_url + "/action";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<ActionListResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        ActionListResult.class);

        return response.getBody();
    }
    
    public ActionCreateResult create() {
        String url = api_url + "/action";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(Action.this, headers);

        HttpEntity<ActionCreateResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        ActionCreateResult.class);   
        id = response.getBody().result.action;
        return response.getBody();
    }
    
    public StringResult edit(){
        return editAction(id);
    }
    
    public StringResult edit(String id){
        return editAction(id);
    }
    
    private StringResult editAction(String id) {
        String url = api_url + "/action/" + id;
        HttpMethod method = HttpMethod.PUT;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(Action.this, headers);

        HttpEntity<StringResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        StringResult.class);

        return response.getBody();
    }
    
    public StringResult delete(){
        return deleteAction(id);
    }
    
    public StringResult delete(String id){
        return deleteAction(id);
    }
    
    private StringResult deleteAction(String id) {
        String url = api_url + "/action/" + id;
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
    
    public ActionInfoResult info(){
        return infoAction(id);
    }
    
    public ActionInfoResult info(String id){
        return infoAction(id);
    }
    
    private ActionInfoResult infoAction(String id) {
        String url = api_url + "/action/" + id;
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<ActionInfoResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        ActionInfoResult.class);

        return response.getBody();
    }
}
