package com.example.leaverequest.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JWTConfig {

    private String secret;
    private long expiration;


    public String getSecret() {
        return secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public long getExpiration() {
        return expiration;
    }
    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    
    
}
