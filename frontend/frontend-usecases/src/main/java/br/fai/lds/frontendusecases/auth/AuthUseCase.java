package br.fai.lds.frontendusecases.auth;

import br.fai.lds.domain.dtos.AuthDto;
import br.fai.lds.domain.user.UserModel;
import br.fai.lds.frontendusecases.port.RestService;

public class AuthUseCase {

    private final RestService<UserModel> restService;

    public AuthUseCase(RestService<UserModel> restService) {
        this.restService = restService;
    }

    public UserModel login(final AuthDto auth){
        if(auth.getUsername().isEmpty()||
                auth.getPassword().isEmpty()) {
            return null;
        }

        final String resource = "/auth/login";

        return restService.postLogin(resource, auth);


    }
}
