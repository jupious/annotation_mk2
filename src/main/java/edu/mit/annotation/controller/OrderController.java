package edu.mit.annotation.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Log4j2
@Controller
public class OrderController {

    @GetMapping("/order/pur-order") //구매발주서 페이지
    public void test(){

    }

    @GetMapping("/order/pur-order-pop") //발주서작성/수정 페이지
    public void test1(){

    }

    @GetMapping("/order/pur-order-print") //발주서프린트 페이지
    public void test2(){

    }

    @GetMapping("/order/progress-check") //진처검수 페이지
    public void test3(){

    }

    @GetMapping("/order/pur-order-report") //진처검수 페이지
    public void test4(){

    }



}



