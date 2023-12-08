package edu.robertmo.newsproject;

import edu.robertmo.newsproject.config.NewsJWTConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(NewsJWTConfig.class)
@ComponentScan(basePackages = "edu.robertmo")
public class NewsProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsProjectApplication.class, args);
    }
}
