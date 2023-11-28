package br.fai.lds.backendusecases.auth;

import br.fai.lds.backendusecases.port.JwtService;
import br.fai.lds.backendusecases.port.PassEncrypter;
import br.fai.lds.backendusecases.port.UserRepository;
import br.fai.lds.domain.dtos.AuthDto;
import br.fai.lds.domain.user.UserModel;

public class AuthUseCase {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final PassEncrypter passEncrypter;

    public AuthUseCase(UserRepository userRepository, JwtService jwtService, PassEncrypter passEncrypter) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passEncrypter = passEncrypter;
    }

    public UserModel login(AuthDto auth){
        UserModel userdb = userRepository.findUserByUsername(auth.getUsername());
        if(!auth.getPassword().equals(userdb.getPassword())){
            return null;
        }
        return userdb;
    }

    public UserModel loginV2(AuthDto authDto){
        if(authDto == null || authDto.getUsername().isEmpty() || authDto.getPassword().isEmpty()) return null;


        //Encrypt the password
//        final String encryptedPassword = passEncrypter.encryptPassword(authDto.getPassword());
//
//        authDto.setPassword(encryptedPassword);

        final UserModel userModel = userRepository.login(authDto.getUsername(), authDto.getPassword());
        if(userModel == null)return null;

        userModel.setPassword("");

        final String token = jwtService.getJwtToken(userModel);
        if(token.equals("INVALID_TOKEN"))return null;

        userModel.setToken(token);

        return userModel;
    }
}
