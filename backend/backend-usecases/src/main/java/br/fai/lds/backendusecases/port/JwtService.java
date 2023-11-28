package br.fai.lds.backendusecases.port;

import br.fai.lds.domain.user.UserModel;

public interface JwtService {
    String getJwtToken(UserModel userModel);
}
