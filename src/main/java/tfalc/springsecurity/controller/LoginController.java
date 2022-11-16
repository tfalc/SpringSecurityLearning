package tfalc.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tfalc.springsecurity.dto.Login;
import tfalc.springsecurity.dto.Sessao;
import tfalc.springsecurity.model.User;
import tfalc.springsecurity.repository.UserRepository;
import tfalc.springsecurity.security.JWTCreator;
import tfalc.springsecurity.security.JWTObject;
import tfalc.springsecurity.security.SecurityConfig;

import java.util.Date;

@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login) {
        User user = userRepository.findByUsername(login.getUsername());
        if (user != null) {
            boolean passwordOk = passwordEncoder.matches(login.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }

            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration(new Date(System.currentTimeMillis() + 3600000));
            jwtObject.setRoles(String.valueOf(user.getRoles()));
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        } else {
            throw new RuntimeException("Erro ao tentar realizar login");
        }
    }
}
