package edu.mit.annotation.controller;

import edu.mit.annotation.realdto.*;
import edu.mit.annotation.service.OrderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orderapi")
@RequiredArgsConstructor
public class OrderAPI {
    private final OrderService orderService;

    @GetMapping("/po-proc-plan")
    public ListWithPaging<ProcPlanNoPO> poProcPlan(String  startDate, String  endDate, String type, String keyword, Integer pageNum, Integer amount) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .pageNum(pageNum*amount)
                .amount(amount)
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDate))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();
        return orderService.getProcPlanListWithNoPO(cri);
    }

    @PostMapping("/po-new-order/{receive_duedate}/{purch_order_detail}")
    public String  poNewOrder(@RequestBody List<NewPurchOrderItem> newPurchOrderItem, @PathVariable("receive_duedate") String  receive_duedate, @PathVariable(value = "purch_order_detail", required = false) String purch_order_detail) throws ParseException {
        System.out.println(newPurchOrderItem+purch_order_detail+receive_duedate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        orderService.savePurchaseOrder(NewPurchaseOrderDTO.builder()
                .newPurchOrderItem(newPurchOrderItem)
                .receive_duedate(sdf.parse(receive_duedate))
                .purch_order_date(new Date())
                .purch_order_detail(purch_order_detail)
                .build());
        return "good";
    }
}
