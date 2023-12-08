package edu.robertmo.newsproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "edu.robertmo.newsproject")
public class NewsJWTConfig {

    /**
     * secret and expires are then set in the application.properties file.
     */
    private String secret = "secret";

    private Long expires = 86400000L;

    public NewsJWTConfig() {

    }
    public NewsJWTConfig(String secret, long expires) {
        this.secret = secret;
        this.expires = expires;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String  secret) {
        this.secret = secret;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

}
