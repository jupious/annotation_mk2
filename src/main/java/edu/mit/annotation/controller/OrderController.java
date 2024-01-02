package edu.mit.annotation.controller;

import edu.mit.annotation.service.CheckService;
import edu.mit.annotation.service.OrderService;
import edu.mit.annotation.testdto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Log4j2
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    CheckService service;

    @GetMapping("/pur-order") //구매발주서 페이지
    public void test(){

    }

    @GetMapping("/pur-order-pop") //발주서작성/수정 페이지
    public void test1(){

    }

    @GetMapping("/pur-order-print") //발주서프린트 페이지
    public void test2(){

    }

    @GetMapping("/progress-check") //진척검수 페이지
    public String CheckList(Model model){
        OrderDTO order = service.orderList();
        model.addAttribute("OrderDTO",order);



        return "/order/progress-check";



    }

    @GetMapping("/pur-order-report") //리포트 페이지
    public void test4(){

    }



}



