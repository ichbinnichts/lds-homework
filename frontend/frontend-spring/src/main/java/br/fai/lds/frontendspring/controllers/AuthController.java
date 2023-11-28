package br.fai.lds.frontendspring.controllers;

import br.fai.lds.domain.dtos.AuthDto;
import br.fai.lds.domain.user.UserModel;
import br.fai.lds.frontendspring.configuration.AuthUseCaseConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthUseCaseConfig authConfig = new AuthUseCaseConfig();

    @PostMapping("/login")
    public String login(AuthDto auth){
        UserModel user = authConfig.authUseCase().login(auth);
        if(user == null){
            return "redirect:/login";
        }
        return "redirect:/";
    }
}
