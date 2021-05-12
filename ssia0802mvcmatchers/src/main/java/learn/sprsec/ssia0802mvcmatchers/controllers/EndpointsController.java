package learn.sprsec.ssia0802mvcmatchers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointsController {

    @PostMapping("/a")
    public String postEndpointA() {
        return "POST /a works!";
    }

    @GetMapping("/a")
    public String getEndpointA() {
        return "GET /a works!";
    }

    @GetMapping("/a/b")
    public String getEndpointB() {
        return "GET /a/b works!";
    }

    @GetMapping("/a/b/c")
    public String getEndpointC() {
        return "GET /a/b/c works!";
    }
}
