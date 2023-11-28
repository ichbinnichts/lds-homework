package br.fai.lds.backendspring.restcontrollers;

import br.fai.lds.backendspring.configuration.FindUserUseCaseConfig;
import br.fai.lds.domain.user.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final FindUserUseCaseConfig findUserConfig = new FindUserUseCaseConfig();

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        List<UserModel> userModelList = findUserConfig.findUser().findAllUsers();
        return userModelList.size() > 0 ? ResponseEntity.ok(userModelList) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable("id") final int id){
        UserModel user = findUserConfig.findUser().findUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
}
