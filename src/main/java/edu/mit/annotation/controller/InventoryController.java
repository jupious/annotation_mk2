package edu.mit.annotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inv")
public class InventoryController {

    @GetMapping("/receiving")
    public void receiving(){

    }
    @GetMapping("/statement")
    public void statement(){

    }
    @GetMapping("/statement-preview")
    public void statementPreview(){

    }
    @GetMapping("/releasing")
    public void releasing(){

    }
    @GetMapping("/inv-calc")
    public void invCalc(){

    }
    @GetMapping("/inv-amount")
    public void invAmount(){}
}
