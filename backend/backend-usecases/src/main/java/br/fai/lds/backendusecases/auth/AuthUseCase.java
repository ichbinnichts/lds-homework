package br.fai.lds.backendusecases.auth;

import br.fai.lds.backendusecases.port.UserRepository;
import br.fai.lds.domain.dtos.AuthDto;
import br.fai.lds.domain.user.UserModel;

public class AuthUseCase {

    private final UserRepository userRepository;

    public AuthUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel login(AuthDto auth){
        UserModel userdb = userRepository.findUserByUsername(auth.getUsername());
        if(!auth.getPassword().equals(userdb.getPassword())){
            return null;
        }
        return userdb;
    }
}
