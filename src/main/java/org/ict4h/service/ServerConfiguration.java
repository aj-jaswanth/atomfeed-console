package org.ict4h.service;

import org.ict4h.domain.Server;
import org.ict4h.domain.Servers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;




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
