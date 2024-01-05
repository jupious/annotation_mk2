package edu.mit.annotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/main/main")
    public void mainPortal(){}


    @GetMapping("/main/login")
    public void mainlogin(){}
}
