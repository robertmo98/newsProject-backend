package edu.robertmo.newsproject;

import edu.robertmo.newsproject.config.NewsConfig;
import edu.robertmo.newsproject.config.NewsJWTConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(NewsJWTConfig.class)
public class NewsProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsProjectApplication.class, args);
    }

}
