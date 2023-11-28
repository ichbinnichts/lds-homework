package br.fai.lds.frontendspring.configuration;

import br.fai.lds.frontendimpl.impl.AccountRestApiController;
import br.fai.lds.frontendusecases.account.AccountUseCase;
import br.fai.lds.frontendusecases.port.AccountRestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountUseCaseConfig {
    @Bean
    public AccountUseCase accountUseCase(){
        AccountRestService accountRestService = new AccountRestApiController();
        return new AccountUseCase(accountRestService);
    }
}
