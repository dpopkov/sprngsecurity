package learn.sprsec.ssia0802mvcmatchers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/product/{code}")
    public String echoProductCode(@PathVariable String code) {
        return code;
    }
}
