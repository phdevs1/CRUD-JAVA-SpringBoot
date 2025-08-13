package pe.example1.Projecto1.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test1Controller {
    @GetMapping("/joder")
    public String saludo(){
        return "hello world1";
    }
}
