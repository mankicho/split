package controller;

import application.ErrorCollector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping(value = "/get/errors/for/debug")
    public HashMap<String, String> adminPage() {
        return ErrorCollector.mailMessage();
    }
}
