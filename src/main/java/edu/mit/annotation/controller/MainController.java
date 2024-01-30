package edu.mit.annotation.controller;

import edu.mit.annotation.dto.CoOpCompany;
import edu.mit.annotation.dto.ProductionPlan;
import edu.mit.annotation.service.ComService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {
    private final ComService service;
    @GetMapping("/portal")
    public void mainPortal(){}


    @GetMapping("/login")
    public String login(){

        return "/main/login";
    }

    @GetMapping("/main")
    public String mainPortal(Model model) {
        List<ProductionPlan> prod = service.calList();
        System.out.println("Controller 에서 받은 값 ::"+prod);
        model.addAttribute("calList",prod);
        return "/main/main";
    }


    @PostMapping("/main.do")
    public String company(CoOpCompany com) {
        System.out.println("컨트롤러에서 뷰로부터 받은 값 :: "+com);

        service.com(com);
//        model.addAttribute("comName",com);
        return "redirect:/main/main";

    }

    @PostMapping("/main.plan")
    public String plan(ProductionPlan prod){
        System.out.println("컨트롤러에서 뷰로부터 받은 값 :: "+prod);
        service.plan(prod);
        return "redirect:/main/main";
    }

    @PostMapping("/main.update")
    public String update(ProductionPlan prod){
        System.out.println("컨트롤러에서 뷰로부터 받은 값 :: "+prod);
        service.update(prod);
        return "redirect:/main/main";
    }

    @PostMapping("/main.delete")
    public String delete(@RequestParam String prod_plan_code){
        System.out.println("컨트롤러에서 뷰로부터 받은 값 :: "+prod_plan_code);
        service.delete(prod_plan_code);
        return "redirect:/main/main";

    }




}
