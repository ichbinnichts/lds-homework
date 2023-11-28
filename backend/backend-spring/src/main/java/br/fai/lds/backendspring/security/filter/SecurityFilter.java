package br.fai.lds.backendspring.security.filter;

import br.fai.lds.backendspring.security.ApiSecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (!checkJwtToken(request)) {
                SecurityContextHolder.clearContext();
                return;
            }

            final Jws<Claims> claims = validateToken(request);

            if (claims == null || claims.getBody().get(ApiSecurityConstants.AUTHORITIES) == null) {
                SecurityContextHolder.clearContext();

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acesso negado");
                return;
            }

            setupSpringAuthentication(claims.getBody());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ocorreu um erro inesperado");
            throw new RuntimeException(e);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    private boolean checkJwtToken(final HttpServletRequest request) {
        final String authorizationHeader = request.getHeader(ApiSecurityConstants.HEADER);

        if (authorizationHeader == null || !authorizationHeader.startsWith(ApiSecurityConstants.PREFIX)) {
            return false;
        }
        return true;
    }

    private Jws<Claims> validateToken(HttpServletRequest request) {
        final String jwtToken = request.getHeader(ApiSecurityConstants.HEADER).replace(ApiSecurityConstants.PREFIX, "");
        return Jwts.parserBuilder()
                .setSigningKey(ApiSecurityConstants.KEY)
                .build()
                .parseClaimsJws(jwtToken);
    }

    private void setupSpringAuthentication(final Claims claims) {
        final List<String> authorities = (List<String>) claims.get(ApiSecurityConstants.AUTHORITIES);

        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                authorities.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
