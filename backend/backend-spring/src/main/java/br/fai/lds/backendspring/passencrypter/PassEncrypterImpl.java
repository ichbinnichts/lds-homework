package br.fai.lds.backendspring.passencrypter;

import br.fai.lds.backendusecases.port.PassEncrypter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassEncrypterImpl implements PassEncrypter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PassEncrypterImpl(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
