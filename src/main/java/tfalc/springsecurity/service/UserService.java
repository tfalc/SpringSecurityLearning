package tfalc.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import tfalc.springsecurity.model.User;
import tfalc.springsecurity.repository.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        String pass = user.getPassword();

        //criptografa antes de salvar em banco
        user.setPassword(passwordEncoder.encode(pass));
        userRepository.save(user);
    }
}
