package edu.mit.annotation.controller;

import edu.mit.annotation.realdto.*;
import edu.mit.annotation.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
                .endDate(sdf.parse(endDateValidate(endDate)))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();

        System.out.println(cri);
        return inventoryService.searchReceiveItemList(cri);
    }

    @GetMapping(value = "/receive-history")
    public Long getReceivedCount(String  startDate, String  endDate, String proc_plan_number) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(endDate);

        return inventoryService.getReceiveHistory(ReceiveHistorySearchDTO.builder()
                                                    .proc_plan_number(proc_plan_number)
                                                    .startDate(sdf.parse(startDate))
                                                    .endDate(sdf.parse(endDate))
                                                    .build());
    }

    @PostMapping("/save-received-item/{proc_plan_number}/{received_quantity}")
    public Integer saveReceivedItem(@PathVariable("proc_plan_number") String proc_plan_number, @PathVariable("received_quantity") Integer received_quantity){

        return inventoryService.saveReceivedItem(ItemSaveDTO.builder()
                                                    .proc_plan_number(proc_plan_number)
                                                    .received_quantity(received_quantity).build());
    }

    @PostMapping("/close-proc-plan/{proc_plan_number}")
    public Integer closeProcPlan(@PathVariable("proc_plan_number") String proc_plan_number){
        return inventoryService.closingProcPlan(proc_plan_number);
    }

    @GetMapping("/auto-search-receive")
    public List<ReceiveItemDTO> autoSearchReceive(String  startDate, String  endDate, String type, String keyword) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDateValidate(endDate)))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();
        return inventoryService.autoReceiveSearch(cri);
    }



    @GetMapping("/statement-get-data")
    public ListWithPaging<ClosedProcPlanDTO> statementGetData(String  startDate, String  endDate, String type, String keyword, Integer pageNum, Integer amount) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .pageNum(pageNum*amount)
                .amount(amount)
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDateValidate(endDate)))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();

        System.out.println(cri);
        return inventoryService.getClosedPrc(cri);
    }

    @GetMapping("/auto-search-statement")
    public List<ClosedProcPlanDTO> autoSearchStatement(String  startDate, String  endDate, String type, String keyword) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDateValidate(endDate)))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();
        return inventoryService.autoPrcpSearch(cri);
    }

    @GetMapping("/release-get-data")
    public ListWithPaging<ReleasingDTO> releaseGetData(String  startDate, String  endDate, String type, String keyword, Integer pageNum, Integer amount) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .pageNum(pageNum*amount)
                .amount(amount)
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDateValidate(endDate)))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();
        return inventoryService.getReleaseData(cri);
    }

    @GetMapping("/auto-search-release")
    public List<ReleasingDTO> autoSearchRelease(String  startDate, String  endDate, String type, String keyword) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDateValidate(endDate)))
                .type(type)
                .keyword("%"+keyword+"%")
                .build();
        return inventoryService.autoSearchRelease(cri);
    }


    @PostMapping("/save-release-item/{item_code}/{release_quantity}")
    public Integer saveReleaseItem(@PathVariable("item_code") String item_code, @PathVariable("release_quantity") Integer release_quantity){
        return inventoryService.saveReleaseItem(ReleaseItemDTO.builder()
                                            .item_code(item_code)
                                            .release_quantity(release_quantity).build());
    }

    @GetMapping("/inv-calc")
    public ListWithPaging<InventoryCalcDTO> invCalc(String  startDate, String  endDate, String type, String keyword, Integer pageNum, Integer amount, String order) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .pageNum(pageNum*amount)
                .amount(amount)
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDateValidate(endDate)))
                .type(type)
                .order(order)
                .keyword("%"+keyword+"%")
                .build();
        return inventoryService.getInvCalcData(cri);
    }

    @GetMapping("/auto-search-inv-calc")
    public List<InventoryCalcDTO> autoSearchInvCalc(String type, String keyword) throws ParseException {
        Criteria cri = Criteria.builder()
                .type(type)
                .keyword("%"+keyword+"%")
                .build();
        return inventoryService.autoSearchInvCalc(cri);
    }



    private String  endDateValidate(String endDate){
        System.out.println(endDate);
        Integer date = Integer.valueOf(endDate.split("-")[2]);
        Integer month = Integer.valueOf(endDate.split("-")[1]);
        Integer year = Integer.valueOf(endDate.split("-")[0]);
        if(month < 8){
            if(month%2 == 1){
                if(date == 31){
                    month+=1;
                    date = 1;
                }else{
                    date += 1;
                }
            }else{
                if(month == 2){
                    if(date==28 || date==29){
                        month += 1;
                        date = 1;
                    }else{
                        date+=1;
                    }
                }else{
                    if(date ==30){
                        month+=1;
                        date = 1;
                    }else{
                        date+=1;
                    }
                }
            }
        }else{
            if(month%2==0){
                if(date==31){
                    date = 1;
                    if(month == 12){
                        year+=1;
                        month = 1;
                    }
                    else{
                        month+=1;
                    }
                }else{
                    date+=1;
                }
            }else{
                if(date==30){
                    month+=1;
                    date = 1;
                }else{
                    date+=1;
                }
            }
        }
        return year+"-"+ (month < 10 ? "0"+month : month) +"-"+ (date < 10? "0"+date : date);
    }

    @GetMapping("/inv-report")
    public List<InventoryReportDTO> invReport(String  startDate, String  endDate, String type) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Criteria cri = Criteria.builder()
                .type(type)
                .startDate(sdf.parse(startDate))
                .endDate(sdf.parse(endDateValidate(endDate)))
                .build();
        return  inventoryService.getInvReport(cri);
    }
}
