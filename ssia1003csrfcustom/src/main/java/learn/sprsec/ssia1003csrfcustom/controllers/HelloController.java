package learn.sprsec.ssia1003csrfcustom.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostMapping("/hello")
    public String postHello() {
        return "POST Hello";
    }

    @PostMapping("/ciao/{number}")
    public String postCiao(@PathVariable String number) {
        return "POST ciao " + number;
    }

}
