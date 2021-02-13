package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/peed")
public class PeedController {

    @PostMapping(value = "/profile/upload")
    public void peed(){

    }
}
