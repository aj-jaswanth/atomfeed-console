package org.ict4h;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class AtomfeedConsoleApplication {
    public static void main(String args[]){
        SpringApplication.run(AtomfeedConsoleApplication.class,args);
    }
}
