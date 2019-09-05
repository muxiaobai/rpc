
package io.github.muxiaobai.spring_cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@SpringBootApplication
public class DemoApplication {
    @GetMapping("/user")
    public  String getUser(String name){
        try{
            int a = 1/0;
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"NOT FOUND");
        }
        return name;
    }
    @GetMapping("/{id}")
    public  String get(@PathVariable(name = "id") String id){
        return id;
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
