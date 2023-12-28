package edu.mit.annotation.controller;

import edu.mit.annotation.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RegisterController {

    RegisterService service;

    @GetMapping("/reg/item")
    public void item()   {
    }

    @GetMapping("/reg/contract")
    public void contract()   {
    }

    @GetMapping("/reg/plan")
    public void plan()   {
    }

}
