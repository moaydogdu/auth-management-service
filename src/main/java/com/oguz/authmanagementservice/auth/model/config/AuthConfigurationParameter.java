package com.oguz.authmanagementservice.auth.model.config;

import com.oguz.authmanagementservice.auth.util.KeyConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.PrivateKey;
import java.security.PublicKey;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "auth")
public class AuthConfigurationParameter {

    private String issuer;
    private Integer accessTokenExpireMinute;
    private Integer refreshTokenExpireDay;
    private String publicKey;
    private String privateKey;

    public PublicKey getPublicKey() {
        return KeyConverter.convertPublicKey(publicKey);
    }

    public PrivateKey getPrivateKey() {
        return KeyConverter.convertPrivateKey(privateKey);
    }

}