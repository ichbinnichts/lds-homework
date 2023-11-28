package br.fai.lds.backendspring.configuration;

import br.fai.lds.backendimpl.repository.UserDao;
import br.fai.lds.backendusecases.auth.AuthUseCase;
import br.fai.lds.backendusecases.port.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCaseConfig {
    @Bean
    public AuthUseCase authUseCase(){
        UserRepository userRepository = new UserDao();
        return new AuthUseCase(userRepository);
    }
}