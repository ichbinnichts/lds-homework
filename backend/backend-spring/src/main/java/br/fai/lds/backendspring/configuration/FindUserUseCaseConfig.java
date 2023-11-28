package br.fai.lds.backendspring.configuration;

import br.fai.lds.backendimpl.repository.UserDao;
import br.fai.lds.backendusecases.port.UserRepository;
import br.fai.lds.backendusecases.user.FindUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindUserUseCaseConfig {
    @Bean
    public FindUser findUser(){
        UserRepository userRepository = new UserDao();
        return new FindUser(userRepository);
    }
}
