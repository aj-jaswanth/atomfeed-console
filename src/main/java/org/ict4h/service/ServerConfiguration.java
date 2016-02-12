package org.ict4h.service;


import org.ict4h.domain.Server;
import org.ict4h.domain.Servers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;




@Component
public class ServerConfiguration {
    private Servers servers= new Servers();
    public  ServerConfiguration() {



        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("data2.yaml");
            RestTemplate restTemplate = new RestTemplate();
            Constructor constructor=new Constructor(Server.class);
            Yaml yaml = new Yaml(constructor);

            for( Object data : yaml.loadAll(in)) {

                Server obj=(Server) data;
                ResponseEntity<String> entity = restTemplate.getForEntity(obj.getServerUrl(), String.class);
                //String body = entity.getBody();
                //MediaType contentType = entity.getHeaders().getContentType();
                HttpStatus statusCode = entity.getStatusCode();

                obj.setStatus(statusCode);
                servers.add(obj);
            }
        }

        catch (Exception e){
            System.out.print(e);
        }

    }
    public Servers getServerDetails(){
        return servers;
    }

}