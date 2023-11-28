package br.fai.lds.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JokeDto {

    private String id;
    private String icon;
    private String url;
    private String value;
}
