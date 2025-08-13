package pe.example1.Projecto1.rest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestRestController {

    @GetMapping("/")
    public String saludo(){
        return "hello world2";
    }
}


