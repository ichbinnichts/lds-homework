package br.fai.lds.backendspring.jwtimpl;

import br.fai.lds.backendspring.security.ApiSecurityConstants;
import br.fai.lds.backendusecases.port.JwtService;
import br.fai.lds.domain.user.UserModel;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtServiceImpl implements JwtService {


    @Override
    public String getJwtToken(UserModel userModel) {
        final List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + userModel.getUserRole()));

        try {
            final String token = Jwts.builder().setId("FAI_LDS_2023")
                    .setSubject(userModel.getUsername())
                    .claim(ApiSecurityConstants.AUTHORITIES, grantedAuthorityList.stream().map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + convertToMilis(10)))
                    .signWith(ApiSecurityConstants.KEY)
                    .compact();
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int convertToMilis(final int minutes) {
        return minutes * 60 * 1000;
    }
}
