package br.fai.lds.frontendspring.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/not-found").permitAll()
                .antMatchers("/access-denied").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .loginProcessingUrl("/auth/login")
                .defaultSuccessUrl("/joke/page")
                .failureHandler((request, response, exception) -> {
                    final FlashMap flashMap = new FlashMap();
                    flashMap.put("loginErrorMessage", "Nome de usuário e/ou senha inválidos");

                    final FlashMapManager flashMapManager = new SessionFlashMapManager();
                    flashMapManager.saveOutputFlashMap(flashMap, request, response);

                    response.sendRedirect("/login");
                })
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");

    }
}
