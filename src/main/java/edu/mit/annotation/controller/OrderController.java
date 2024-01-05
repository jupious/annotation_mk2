package edu.mit.annotation.controller;


import edu.mit.annotation.test2dto.CheckDTO;
import edu.mit.annotation.test2dto.OrderDTO;
import edu.mit.annotation.test2dto.TestItemDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @GetMapping("/order/progress-check") //진척검수 페이지
    public String CheckList(Model model){
        //orderdto객체 생성
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<OrderDTO> orders = new ArrayList<>();
        for(int i=1; i<=5; i++){

            OrderDTO order = new OrderDTO();
            order.setPoCode("P-000"+i);
            order.setComName("협력업체"+i);
            order.setPName("A2"+i);
            order.setResultnum(i+"차완료");
            order.setProgress(20+i*10);
            order.setOrderDate("2023-11-2"+i);
            order.setDelDate("2023-01-2"+i);
            order.setBusinessNum("192-168-35-12"+i);
            order.setEName("이준영"+i);


            orders.add(order); // 리스트에 객체를 추가
        }
        //Itemdto 생성
        List<TestItemDTO> items = new ArrayList<>();
        for(int i=1; i<=2; i++){
            TestItemDTO item = new TestItemDTO();
            item.setNum(i);
            item.setICode("A2"+i);
            item.setIName("품목"+i);
            item.setCount(i);
            item.setUnitPrice(10500+i);
            item.setSupPrice(20500+i);

            items.add(item);
        }
        // CheckDTO 생성
        List<CheckDTO> checks = new ArrayList<>();
        for(int i=1; i<=2; i++){
            CheckDTO check = new CheckDTO();
            check.setNum(i);
            check.setResult(i+"차완료");
//            order.setCheckDate("2024-01-0"+i);
            String dateString = "2023-01-0" + i; // 예시 날짜 문자열
            try {
                Date date = dateFormat.parse(dateString); // 문자열을 Date 객체로 파싱
                check.setCheckDate(date); // CheckDate에 설정
            } catch (ParseException e) {
                e.printStackTrace();
            }
            check.setSum(i*10);
            check.setEtc("");

            checks.add(check);
        }
        model.addAttribute("checkList",checks);
        model.addAttribute("itemList",items);
        model.addAttribute("orderList",orders);
        return "/order/progress-check";



    }
    @GetMapping("/order/pur-order-content") //발주서 내용
    public void test3(){

    }

    @GetMapping("/order/pur-order-report") //리포트 페이지
    public void test4(){

    }



}



