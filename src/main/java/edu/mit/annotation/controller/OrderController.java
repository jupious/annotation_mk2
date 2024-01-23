package edu.mit.annotation.controller;


import edu.mit.annotation.realdto.PurchOrderItemsDTO;
import edu.mit.annotation.service.CheckService;
import edu.mit.annotation.service.OrderService;
import edu.mit.annotation.test2dto.CheckInfo;
import edu.mit.annotation.test2dto.CheckList;
import edu.mit.annotation.test2dto.OrderInfo;
import edu.mit.annotation.test2dto.OrderList;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Log4j2
@Controller
public class OrderController {

    private final OrderService orderService;
    private final CheckService service;

    @GetMapping("/order/pur-order") //구매발주서 페이지
    public void purOrder(Model model){
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String nowStr = dtm.format(nowDate);
        String monthAfter= dtm.format(nowDate.plusMonths(1L));


        model.addAttribute("defaultStartDate",nowStr);
        model.addAttribute("defaultEndDate",monthAfter);

    }

    @GetMapping("/order/pur-order-pop/{business_number}/{prcpNumbers}") //발주서작성/수정 페이지
    public String  purOrderPop(@PathVariable("business_number") String business_number, @PathVariable("prcpNumbers") String prcpNumbers, Model model){
        System.out.println(business_number);
        System.out.println("'"+prcpNumbers+"'");
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowStr = dtm.format(nowDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        List<PurchOrderItemsDTO> list = orderService.getPOItems("'"+prcpNumbers+"'");
        model.addAttribute("dueDate",sdf.format(list.get(0).getProc_duedate()));
        model.addAttribute("today",nowStr);
        model.addAttribute("companyInfo",orderService.getCompInfo(business_number));
        model.addAttribute("itemList",list);

        return "/order/pur-order-pop";
    }

    @GetMapping("/order/pur-order-print") //발주서프린트 페이지
    public void test2(){

    }

    @GetMapping("/order/progress-check") //진척검수 페이지
    public String CheckList(Model model){
        List<OrderList> orderList= service.selectOrderList();
        List<CheckList> checkList = service.selectCheckList();
        model.addAttribute("checkList", checkList);
        model.addAttribute("List", orderList);
        System.out.println("CheckList" + checkList);
        System.out.println("OrderList" + orderList);
        return "/order/progress-check";



    }
    @PostMapping("/order/progress-check")
    @ResponseBody
    public OrderInfo detailList(@RequestParam String id, @RequestParam String code, Model model) {
        System.out.println("컨트롤러에서 ajax를 통해 받은 purch_order_number 값"+id+"item_code 값 :" + code);
        OrderInfo orderinfo = service.selectpoinfo(id,code);
        System.out.println("orderinfo 값@@@@ :"+orderinfo);
        model.addAttribute("orderinfo",orderinfo);

        return orderinfo;
    }

    @PostMapping("/order/progress-check/itemPlan") // 진척검수일정 만들기
    @ResponseBody
    public void itemPlan(@RequestParam String p1, @RequestParam String p10,
                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date p10_1,
                         @RequestParam String p11, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date p11_1) {
        service.progressPlan(p1,p10,p10_1,p11,p11_1);
    }

    @GetMapping("/order/progress-check/dateCheck")
    public String dateCheck(@RequestParam("date1") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                            @RequestParam("date2") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2,
                            Model model) {
        List<OrderList> list=service.dateSelect(date1,date2);
        model.addAttribute("List",list);

        return "/order/progress-check";
    }

    @GetMapping("/order/progress-check/searchPlan")
    public String searchPlan(@RequestParam("search1") String search,@RequestParam("option") String option,Model model){
        List<OrderList> list = service.searchPlan(search,option);
        model.addAttribute("List",list);
        return "/order/progress-check";
    }

    @PostMapping("/order/progress-check/checkItem")
    @ResponseBody
    public CheckInfo checkInfo(@RequestParam String number, @RequestParam String code, Model model) {
        System.out.println("컨트롤러에서 ajax를 통해 받은 purch_order_number 값"+number+"item_code 값 :" + code);
        CheckInfo checkInfo = service.selectCheckinfo(number,code);
        System.out.println("orderinfo 값@@@@ :"+checkInfo);
        model.addAttribute("checkinfo",checkInfo);

        return checkInfo;
    }


    @GetMapping("/order/pur-order-report") //리포트 페이지
    public void test4(){

    }

    @GetMapping("/order/pur-order-content") //발주서 내용
    public void test3(){

    }



}



