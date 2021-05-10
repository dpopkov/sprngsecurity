package learn.sprsec.ssia0601web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/main")
    public String mainPage() {
        return "main.html";
    }
}
