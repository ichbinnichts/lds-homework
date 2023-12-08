package br.fai.lds.backendusecases.user;

import br.fai.lds.backendusecases.port.UserRepository;
import br.fai.lds.domain.user.UserModel;
import br.fai.lds.domain.user.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class FindUserTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private FindUser findUser;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllUsersSuccess(){

        when(userRepository.findAllUsers()).thenReturn(createUserList());

        List<UserModel> userModelList = findUser.findAllUsers();

        Assertions.assertTrue(userModelList.size() > 1);
    }

    private List<UserModel> createUserList(){
        List<UserModel> userModelList = new ArrayList<>();

        userModelList.add(UserModel.builder().id(1).username("Nathan Teste 1").password("mypass1").userRole(UserRole.ADMIN).token("").build());
        userModelList.add(UserModel.builder().id(2).username("Nathan Faria Teste 2").password("mypass2").userRole(UserRole.USER).token("").build());
        userModelList.add(UserModel.builder().id(3).username("Nathan Victor de Faria Teste 3").password("mypass3").userRole(UserRole.HEALTH_PROFESSIONAL).token("").build());

        return userModelList;
    }
}
