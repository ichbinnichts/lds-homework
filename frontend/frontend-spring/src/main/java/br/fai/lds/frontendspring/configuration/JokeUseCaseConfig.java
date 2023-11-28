package br.fai.lds.frontendspring.configuration;

import br.fai.lds.domain.dtos.JokeDto;
import br.fai.lds.frontendimpl.impl.RestApiController;
import br.fai.lds.frontendusecases.joke.JokeUseCase;
import br.fai.lds.frontendusecases.port.RestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JokeUseCaseConfig {
    @Bean
    public JokeUseCase jokeUseCase(){
        RestService<JokeDto> restService = new RestApiController<>();
        return new JokeUseCase(restService);
    }
}
