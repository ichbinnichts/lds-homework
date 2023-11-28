package br.fai.lds.backendspring.configuration;

import br.fai.lds.backendimpl.repository.UserDao;
import br.fai.lds.backendspring.jwtimpl.JwtServiceImpl;
import br.fai.lds.backendspring.passencrypter.PassEncrypterImpl;
import br.fai.lds.backendusecases.auth.AuthUseCase;
import br.fai.lds.backendusecases.port.JwtService;
import br.fai.lds.backendusecases.port.PassEncrypter;
import br.fai.lds.backendusecases.port.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthUseCaseConfig {
    @Bean
    public AuthUseCase authUseCase(){
        UserRepository userRepository = new UserDao();
        JwtService jwtService = new JwtServiceImpl();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        PassEncrypter passEncrypter = new PassEncrypterImpl(bCryptPasswordEncoder);

        return new AuthUseCase(userRepository, jwtService, passEncrypter);
    }
}