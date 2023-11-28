package br.fai.lds.frontendspring.controllers;

import br.fai.lds.domain.dtos.JokeDto;
import br.fai.lds.frontendspring.configuration.JokeUseCaseConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/joke")
public class JokeController {

    private final JokeUseCaseConfig jokeUseCaseConfig = new JokeUseCaseConfig();

    @GetMapping("/page")
    public String getJokePage(Model model){
        JokeDto jokeDto = jokeUseCaseConfig.jokeUseCase().getJoke();
        model.addAttribute("joke", jokeDto);
        return "joke/page";
    }
}
