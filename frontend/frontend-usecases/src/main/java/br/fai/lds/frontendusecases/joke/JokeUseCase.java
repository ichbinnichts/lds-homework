package br.fai.lds.frontendusecases.joke;

import br.fai.lds.domain.dtos.JokeDto;
import br.fai.lds.frontendusecases.port.RestService;

public class JokeUseCase {
    private final RestService<JokeDto> restService;

    public JokeUseCase(RestService<JokeDto> restService) {
        this.restService = restService;
    }

    public JokeDto getJoke(){
        final String resource = "https://api.chucknorris.io/jokes/random";
        final String jokeString = restService.getRoot(resource);
        JokeDto joke = new JokeDto();
        joke.setValue(jokeString);

        return joke;

    }
}
