package br.fai.lds.frontendimpl.impl;

import br.fai.lds.domain.user.UserModel;
import br.fai.lds.frontendusecases.port.AccountRestService;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class AccountRestApiController implements AccountRestService {

    private HttpHeaders getBasicHeaders(final String username, final String password) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("username", username);
        httpHeaders.set("password", password);

        return httpHeaders;
    }
    @Override
    public UserModel validateCredentials(String username, String password) {
        try {
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<>(getBasicHeaders(username, password));

            final String endpoint = "http://localhost:8081/api/auth/login";
            ResponseEntity<UserModel> responseEntity = restTemplate.exchange(
                    endpoint,
                    HttpMethod.POST,
                    httpEntity,
                    UserModel.class);
            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                return null;
            }
            UserModel user = responseEntity.getBody();
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
