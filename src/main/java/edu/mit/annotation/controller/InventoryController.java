package edu.mit.annotation.controller;

import edu.mit.annotation.realdto.StatementItemsDTO;
import edu.mit.annotation.realdto.StatementPreviewDTO;
import edu.mit.annotation.service.InventoryService;
import edu.mit.annotation.testdto.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
@RequestMapping("/inv")
@AllArgsConstructor
public class InventoryController {

    private InventoryService inventoryService;

    @GetMapping("/receiving")
    public void receiving(Model model){
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String nowStr = dtm.format(nowDate);
        String monthAfter = dtm.format(nowDate.plusMonths(1L));


        model.addAttribute("defaultStartDate",nowStr);
        model.addAttribute("defaultEndDate",monthAfter);
    }
    @GetMapping("/statement")
    public void statement(Model model) throws ParseException {
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String nowStr = dtm.format(nowDate);
        String monthAfter = dtm.format(nowDate.plusMonths(1L));


        model.addAttribute("defaultStartDate",nowStr);
        model.addAttribute("defaultEndDate",monthAfter);
    }

    @GetMapping("/statement-preview/{purch_order_number}")
    public String statementPreview(@PathVariable String purch_order_number, Model model){
        StatementPreviewDTO dto = inventoryService.getStatement(purch_order_number);
        int totalPrice = 0;
        for (StatementItemsDTO d: dto.getItemList()) {
            d.setProd_price((int) (d.getItem_price() * d.getReceived_quantity()));
            totalPrice += d.getProd_price();
        }

        model.addAttribute("data",dto);
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("poNum",purch_order_number);
        return "/inv/statement-preview";
    }
    @GetMapping("/releasing")
    public void releasing(Model model) throws ParseException {
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String nowStr = dtm.format(nowDate);
        String monthAfter = dtm.format(nowDate.plusMonths(1L));


        model.addAttribute("defaultStartDate",nowStr);
        model.addAttribute("defaultEndDate",monthAfter);
    }
    @GetMapping("/inv-calc")
    public void invCalc(Model model){
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String monthBefore= dtm.format(nowDate.minusMonths(1L));
        String nowStr = dtm.format(nowDate);


        model.addAttribute("defaultStartDate",monthBefore);
        model.addAttribute("defaultEndDate",nowStr);

    }
    @GetMapping("/inv-amount")
    public void invAmount(Model model){
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String nowStr = dtm.format(nowDate);


        model.addAttribute("defaultDate",nowStr);

        //이 아래로 지워
        Random rand = new Random();
        String[] unitCate = {"관제부","압착부","용접부","장착부","절단부","정렬부","제어부","주입부","충전부"};
        Integer[] unitAmount = new Integer[unitCate.length];
        String[] assyCate = {"감지기", "고정대", "제어판", "이슈", "구동축", "모터", "실린더", "레이저", "서브모터", "펌프", "전자저울", "커넥터", "투영기"};
        Integer[] assyAmount = new Integer[assyCate.length];

        String[] partCate = {"센서", "브라켓", "릴레이", "기어", "솔레노이드벨브", "점멸램프","PLC","엔코더","베어링","케이블","트랜스미션","볼트너트","피팅류","JIG","호스"};
        Integer[] partAmount = new Integer[partCate.length];

        for(int i = 0; i < unitCate.length; i++){
            unitAmount[i] = rand.nextInt(700)+150;
        }
        for(int i = 0; i < assyCate.length; i++){
            assyAmount[i] = rand.nextInt(700)+150;
        }
        for(int i = 0; i < partCate.length; i++){
            partAmount[i] = rand.nextInt(700)+150;
        }
        model.addAttribute("unitCategories",unitCate);
        model.addAttribute("unitAmounts",unitAmount);
        model.addAttribute("assyCategories",assyCate);
        model.addAttribute("assyAmounts",assyAmount);
        model.addAttribute("partCategories",partCate);
        model.addAttribute("partAmounts",partAmount);
    }

    
}
