package br.fai.lds.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private int id;
    private String username;
    private String password;

    private UserRole userRole;

    private String token;
}
