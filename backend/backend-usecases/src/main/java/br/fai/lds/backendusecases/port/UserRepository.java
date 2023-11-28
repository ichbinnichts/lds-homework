package br.fai.lds.backendusecases.port;

import br.fai.lds.domain.user.UserModel;

import java.util.List;

public interface UserRepository {
    UserModel findUserById(final int id);

    List<UserModel> findAllUsers();

    UserModel findUserByUsername(final String username);
}
