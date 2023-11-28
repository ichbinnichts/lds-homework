package br.fai.lds.frontendusecases.account;

import br.fai.lds.domain.user.UserModel;
import br.fai.lds.frontendusecases.port.AccountRestService;

public class AccountUseCase {
    private final AccountRestService accountRestService;

    public AccountUseCase(AccountRestService accountRestService) {
        this.accountRestService = accountRestService;
    }
    public UserModel login(final String username, final String password){
        if (username == null) {
            return null;
        }

        if (password == null
                || password.length() < 4) {
            return null;
        }

        UserModel userModel = accountRestService
                .validateCredentials(username, password);

        return userModel;
    }
}
