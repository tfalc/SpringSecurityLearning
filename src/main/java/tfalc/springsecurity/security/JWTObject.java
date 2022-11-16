package tfalc.springsecurity.security;

import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class JWTObject {
    private String subject; //username
    private Date issuedAt; //creation date
    private Date expiration; //expiration date
    private List<String> roles; //roles

    public void setRoles(String... roles) {
        this.roles = Arrays.asList(roles);
    }
}
