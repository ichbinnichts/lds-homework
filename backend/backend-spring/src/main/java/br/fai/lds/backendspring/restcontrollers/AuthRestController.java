package br.fai.lds.backendspring.restcontrollers;

import br.fai.lds.backendspring.configuration.AuthUseCaseConfig;
import br.fai.lds.domain.dtos.AuthDto;
import br.fai.lds.domain.dtos.LoginResponseDto;
import br.fai.lds.domain.user.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AuthUseCaseConfig authConfig = new AuthUseCaseConfig();

    //Login without jwt token
//    @PostMapping("/login")
//    public ResponseEntity<UserModel> login(@RequestBody AuthDto auth){
//        UserModel user = authConfig.authUseCase().login(auth);
//        return user != null ? ResponseEntity.ok(user) : ResponseEntity.badRequest().build();
//    }


    //Login with jwt token

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestHeader("username")final String username, @RequestHeader("password")final String password){
        AuthDto authDto = new AuthDto(username, password);

        UserModel userModel = authConfig.authUseCase().loginV2(authDto);

        if(userModel == null)return ResponseEntity.badRequest().build();

        LoginResponseDto loginResponseDto = new  LoginResponseDto(userModel.getId(), userModel.getUsername(), userModel.getToken());

        return loginResponseDto != null ? ResponseEntity.ok(loginResponseDto) : ResponseEntity.badRequest().build();
    }
}
