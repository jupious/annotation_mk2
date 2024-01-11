package edu.mit.annotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @GetMapping("/main/main")
    public void mainPortal(){}


    @GetMapping("/login")
    public String login(){
        return "/main/login";
    }


}
