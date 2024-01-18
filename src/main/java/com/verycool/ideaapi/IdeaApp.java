package com.verycool.ideaapi;

import com.verycool.ideaapi.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class IdeaApp {

    public static void main(String[] args) {
        SpringApplication.run(IdeaApp.class, args);
    }

}
