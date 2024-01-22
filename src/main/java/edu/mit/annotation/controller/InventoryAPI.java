package edu.mit.annotation.controller;

import edu.mit.annotation.realdto.*;
import edu.mit.annotation.service.InventoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/invapi")
@RequiredArgsConstructor
public class InventoryAPI {

    private final InventoryService inventoryService;



    @GetMapping(value = "/receive-get-data")
    public ListWithPaging<ReceiveItemDTO> getReceiveData(String  startDate, String  endDate, String type, String keyword, Integer pageNum, Integer amount) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .pageNum(pageNum*amount)
                .amount(amount)
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDate))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();

        System.out.println(cri);
        return inventoryService.searchReceiveItemList(cri);
    }

    @GetMapping(value = "/receive-history")
    public Long getReceivedCount(String  startDate, String  endDate, String item_code) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return inventoryService.getReceiveHistory(SearchDTO.builder()
                                                    .item_code(item_code)
                                                    .startDate(sdf.parse(startDate))
                                                    .endDate(sdf.parse(endDate))
                                                    .build());
    }

    @PostMapping("/save-received-item/{item_code}/{received_quantity}")
    public Integer saveReceivedItem(@PathVariable("item_code") String item_code, @PathVariable("received_quantity") Integer received_quantity){

        return inventoryService.saveReceivedItem(ItemSaveDTO.builder()
                                                    .item_code(item_code)
                                                    .received_quantity(received_quantity).build());
    }

    @PostMapping("/close-proc-plan/{proc_plan_number}")
    public Integer closeProcPlan(@PathVariable("proc_plan_number") String proc_plan_number){
        return inventoryService.closingProcPlan(proc_plan_number);
    }

    @PostMapping("/close-purchase-order/{proc_plan_number}")
    public void closePurchaseOrder(@PathVariable("proc_plan_number") String proc_plan_number){
        inventoryService.closingPO(proc_plan_number);
    }

    @GetMapping("/statement-get-data")
    public ListWithPaging<ClosedPurchaseOrderDTO> statementGetData(String  startDate, String  endDate, String type, String keyword, Integer pageNum, Integer amount) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .pageNum(pageNum*amount)
                .amount(amount)
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDate))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();

        System.out.println(cri);
        return inventoryService.getClosedPO(cri);
    }

    @GetMapping("/release-get-data")
    public ListWithPaging<ReleasingDTO> releaseGetData(String  startDate, String  endDate, String type, String keyword, Integer pageNum, Integer amount) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .pageNum(pageNum*amount)
                .amount(amount)
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDate))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();
        return inventoryService.getReleaseData(cri);
    }

    @GetMapping("/statement-published")
    public void statementPublished(String purch_order_number){
        inventoryService.stmtPbCntUp(purch_order_number);
    }

    @PostMapping("/save-release-item/{item_code}/{release_quantity}")
    public Integer saveReleaseItem(@PathVariable("item_code") String item_code, @PathVariable("release_quantity") Integer release_quantity){
        return inventoryService.saveReleaseItem(ReleaseItemDTO.builder()
                                            .item_code(item_code)
                                            .release_quantity(release_quantity).build());
    }

    @GetMapping("/inv-calc")
    public ListWithPaging<InventoryCalcDTO> invCalc(String  startDate, String  endDate, String type, String keyword, Integer pageNum, Integer amount) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .pageNum(pageNum*amount)
                .amount(amount)
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDate))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();
        return inventoryService.getInvCalcData(cri);
    }

    @GetMapping("/inv-report")
    public List<InventoryReportDTO> invReport(String  startDate, String  endDate, String type) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .type(type)
                .keyword(type+"_code")
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDate))
                .build();
        return  inventoryService.getInvReport(cri);
    }
}
