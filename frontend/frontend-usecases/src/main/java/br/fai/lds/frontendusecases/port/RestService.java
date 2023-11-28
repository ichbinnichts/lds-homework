package br.fai.lds.frontendusecases.port;

import br.fai.lds.domain.dtos.AuthDto;
import br.fai.lds.domain.user.UserModel;

import java.util.List;

public interface RestService <T>{
    List<T> get(final String resource);

    T get(final String resource, Class<T> clazz);

    int post(final String resource, final T entity);

    boolean put(final String resource, final T entity);

    boolean delete(final String resource);

    UserModel postLogin(String resource, AuthDto user);

    String getRoot(final String resource);
}
