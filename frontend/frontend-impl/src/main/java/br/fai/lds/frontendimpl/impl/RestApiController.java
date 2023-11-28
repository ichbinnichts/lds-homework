package br.fai.lds.frontendimpl.impl;

import br.fai.lds.domain.dtos.AuthDto;
import br.fai.lds.domain.user.UserModel;
import br.fai.lds.domain.user.UserRole;
import br.fai.lds.frontendusecases.port.RestService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestApiController<T> implements RestService<T> {

    private static final String BASE_ENDPOINT = "http://localhost:8081/api";

    private String getEndpoint (final String resource) {
        return BASE_ENDPOINT + resource;
    }

    @Override
    public List<T> get(String resource) {



        final String endpoint = getEndpoint(resource);
        final RestTemplate restTemplate = new RestTemplate();
        final HttpEntity<String> httpEntity = new HttpEntity<>("");

        ResponseEntity<List<T>> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<>() {
                });

        List<T> body = responseEntity.getBody();

        return body;
    }

    @Override
    public T get(String resource, Class<T> clazz) {

        try {

            final String endpoint = getEndpoint(resource);
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<>("");

            final ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, String.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK) {
                final Gson gson = new Gson();
                final T responseObject = gson.fromJson(responseEntity.getBody(), clazz);
                return responseObject;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public int post(String resource, T entity) {
        try {

            final String endpoint = getEndpoint(resource);
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<T> httpEntity = new HttpEntity<>(entity);

            final ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, String.class);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public boolean put(String resource, T entity) {
        return false;
    }

    @Override
    public boolean delete(String resource) {
        return false;
    }

    @Override
    public UserModel postLogin(String resource, AuthDto auth) {
        try {

            final String endpoint = getEndpoint(resource);
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<AuthDto> httpEntity = new HttpEntity<>(auth);

            final ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint, HttpMethod.POST, httpEntity, String.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                ObjectMapper obj = new ObjectMapper();
                JsonNode rootNode = obj.readTree(responseEntity.getBody());
                if(rootNode.has("username") && rootNode.has("password")){
                    return new UserModel(rootNode.get("id").asInt(), rootNode.get("username").asText(), rootNode.get("password").asText(), UserRole.valueOf(rootNode.get("userRole").asText()), rootNode.get("token").asText());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getRoot(String resourceRoot, Class<T> clazz) {
        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<>("");

            final ResponseEntity<String> responseEntity = restTemplate.exchange(resourceRoot, HttpMethod.GET, httpEntity, String.class);

            if(responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
