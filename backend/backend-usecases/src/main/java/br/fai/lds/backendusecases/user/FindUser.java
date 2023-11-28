package br.fai.lds.backendusecases.user;

import br.fai.lds.backendusecases.port.UserRepository;
import br.fai.lds.domain.user.UserModel;

import java.util.List;

public class FindUser {

    private final UserRepository userRepository;

    public FindUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel findUserById(final int id){
        if(id <= 0){
            return null;
        }

        return userRepository.findUserById(id);
    }

    public List<UserModel> findAllUsers(){
        return userRepository.findAllUsers();
    }
}