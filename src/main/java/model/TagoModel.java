package model;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public abstract class TagoModel {
    protected String token;
    protected String api_url;
    protected HttpHeaders headers;
    protected RestTemplate restTemplate;
    protected Config config;
    
    public Date created_at;
    public Date updated_at;
    
    protected void loadConfig() {
        config = new Config();
        api_url = config.app_url;
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(new MappingJackson2HttpMessageConverter());
    }

    protected TagoModel(String token) {
        loadConfig();
        setToken(token);
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
        headers.add("Device-Token", token);
    }
    
    

}
