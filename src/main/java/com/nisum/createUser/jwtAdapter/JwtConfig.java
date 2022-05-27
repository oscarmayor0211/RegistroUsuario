package com.nisum.createUser.jwtAdapter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Configuration
@ConfigurationProperties(prefix = "application.config")
@Data
@Validated
public class JwtConfig {

    @NotBlank
    public String jwtSecret;

    @NotBlank
    public String patternPassword;
}
