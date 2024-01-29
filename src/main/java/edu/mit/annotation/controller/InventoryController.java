package edu.mit.annotation.controller;

import edu.mit.annotation.realdto.StatementItemDTO;
import edu.mit.annotation.realdto.StatementPreviewDTO;
import edu.mit.annotation.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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

    @GetMapping("/statement-preview/{proc_plan_number}/{business_number}")
    public String statementPreview(@PathVariable String proc_plan_number,@PathVariable String business_number ,Model model){
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String nowStr = dtm.format(nowDate);

        StatementPreviewDTO dto = inventoryService.getStatement("'"+proc_plan_number+"'", business_number);
        int totalPrice = 0;
        for (StatementItemDTO d: dto.getItemList()) {
            d.setProd_price((d.getItem_price() * d.getReceive_quantity()));
            totalPrice += d.getProd_price();
        }

        model.addAttribute("today",nowStr);
        model.addAttribute("data",dto);
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("bsno", business_number);
        model.addAttribute("ppno",proc_plan_number);
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

    }

    
}
