package model.analysis;

import domain.AnalysisCreateResult;
import domain.AnalysisResult;
import domain.AnalysisTokenResult;
import domain.StringResult;
import java.util.List;
import model.TagoModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public class Analysis extends TagoModel {

    public String last_run;
    public String file_name;
    public List<KeyVal> tags;
    public List<KeyVal> variables;
    public String id;
    public String name;
    public String description;
    public String interval;
    public String run_on;
    public Boolean active;
    public String locked_at;
    public String language;
    public List<String> console;

    public Analysis(String token) {
        super(token);
    }

    public Analysis() {
        super(System.getenv("ACCOUNT_TOKEN"));
    }

    public AnalysisResult list() {
        String url = api_url + "/analysis";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<AnalysisResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AnalysisResult.class);

        return response.getBody();
    }

    public AnalysisCreateResult create() {
        String url = api_url + "/analysis";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(Analysis.this, headers);

        HttpEntity<AnalysisCreateResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AnalysisCreateResult.class);

        id = response.getBody().result.id;

        return response.getBody();
    }

    public AnalysisCreateResult edit(Analysis analysis) {
        String url = api_url + "/analysis/" + analysis.id;
        HttpMethod method = HttpMethod.PUT;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(analysis, headers);

        HttpEntity<AnalysisCreateResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AnalysisCreateResult.class);

        return response.getBody();
    }

    private StringResult deleteAnalysis(String id) {
        String url = api_url + "/analysis/" + id;
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

    public StringResult delete() {
        return deleteAnalysis(id);
    }

    public StringResult delete(String id) {
        return deleteAnalysis(id);
    }

    public AnalysisCreateResult info() {
        return infoAnalysis(id);
    }

    public AnalysisCreateResult info(String id) {
        return infoAnalysis(id);
    }

    private AnalysisCreateResult infoAnalysis(String id) {
        String url = api_url + "/analysis/" + id;
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<AnalysisCreateResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AnalysisCreateResult.class);

        return response.getBody();
    }

    public StringResult run() {
        return runAnalysis(id);
    }

    public StringResult run(String id) {
        return runAnalysis(id);
    }

    private StringResult runAnalysis(String id) {
        String url = api_url + "/analysis/" + id + "/run";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<StringResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        StringResult.class);

        return response.getBody();
    }

    public AnalysisTokenResult tokenGenerate() {
        String url = api_url + "/analysis/" + id + "/token";
        HttpMethod method = HttpMethod.GET;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(headers);

        HttpEntity<AnalysisTokenResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        AnalysisTokenResult.class);

        return response.getBody();
    }

    public StringResult uploadScript(AnalysisUpload upload) {
        String url = api_url + "/analysis/" + id + "/upload";
        HttpMethod method = HttpMethod.POST;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        HttpEntity entity = new HttpEntity(upload, headers);

        HttpEntity<StringResult> response = restTemplate
                .exchange(builder.build().toUriString(),
                        method,
                        entity,
                        StringResult.class);

        return response.getBody();
    }
}
