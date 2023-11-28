package br.fai.lds.frontendusecases.port;

import br.fai.lds.domain.user.UserModel;

public interface AccountRestService {
    UserModel validateCredentials(final String username, final String password);
}
