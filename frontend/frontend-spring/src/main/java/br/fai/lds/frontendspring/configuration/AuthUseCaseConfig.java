package br.fai.lds.frontendspring.configuration;

import br.fai.lds.domain.user.UserModel;
import br.fai.lds.frontendimpl.impl.RestApiController;
import br.fai.lds.frontendusecases.auth.AuthUseCase;
import br.fai.lds.frontendusecases.port.RestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCaseConfig {
    @Bean
    public AuthUseCase authUseCase(){
        RestService<UserModel> restService = new RestApiController();
        return new AuthUseCase(restService);
    }
}
