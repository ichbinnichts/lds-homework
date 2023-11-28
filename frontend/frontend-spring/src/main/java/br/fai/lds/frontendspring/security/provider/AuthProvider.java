package br.fai.lds.frontendspring.security.provider;

import br.fai.lds.domain.user.UserModel;
import br.fai.lds.frontendusecases.account.AccountUseCase;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProvider implements AuthenticationProvider {

    private final AccountUseCase accountUseCase;

    public AuthProvider(AccountUseCase accountUseCase) {
        this.accountUseCase = accountUseCase;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        UserModel user = accountUseCase.login(username, password);

        if (user == null) {
            throw new UsernameNotFoundException("username not found");
        }

        final List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()));

        final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final HttpSession httpSession = attributes.getRequest().getSession(false);
        httpSession.setAttribute("userAccount", user);

        return new UsernamePasswordAuthenticationToken(user, password, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
