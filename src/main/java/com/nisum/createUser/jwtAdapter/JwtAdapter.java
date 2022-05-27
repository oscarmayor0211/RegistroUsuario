package com.nisum.createUser.jwtAdapter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtAdapter {

    @Autowired
    private JwtConfig config;

    public String generateToken(String uuid) {
        Algorithm algorithm = Algorithm.HMAC256(config.getJwtSecret());
        return JWT
                .create()
                .withIssuer(uuid)
                .sign(algorithm);
    }
}