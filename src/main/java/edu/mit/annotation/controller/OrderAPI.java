package edu.mit.annotation.controller;

import edu.mit.annotation.realdto.*;
import edu.mit.annotation.service.OrderService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/po-new-order")
    public String  poNewOrder(NewPurchaseOrderDTO newPurchaseOrderDTO){
        System.out.println(newPurchaseOrderDTO);
        return "good";
    }
}
