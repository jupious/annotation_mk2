package edu.mit.annotation.controller;


import edu.mit.annotation.realdto.MemberDTO;
import edu.mit.annotation.realdto.PurchOrderItemsDTO;
import edu.mit.annotation.service.OrderService;
import edu.mit.annotation.test2dto.CheckInfo;
import edu.mit.annotation.test2dto.CheckList;
import edu.mit.annotation.test2dto.OrderInfo;
import edu.mit.annotation.test2dto.OrderList;
import edu.mit.annotation.testdto.PageDTO;
import edu.mit.annotation.testdto.ProgressCheckDTO;
import edu.mit.annotation.testdto.RegisterCriteria;
import edu.mit.annotation.testdto.getCompanyDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@AllArgsConstructor
@Log4j2
@Controller
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order/pur-order") //구매발주서 페이지
    public void purOrder(Model model){
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String nowStr = dtm.format(nowDate);
        String monthAfter= dtm.format(nowDate.plusMonths(1L));
        String monthBefore= dtm.format(nowDate.minusMonths(1L));

        model.addAttribute("defaultStartDate",nowStr);
        model.addAttribute("defaultLowerDate",monthBefore);
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
        model.addAttribute("today",nowStr);
        model.addAttribute("companyInfo",orderService.getCompInfo(business_number));
        model.addAttribute("itemList",list);

        return "/order/pur-order-pop";
    }

    @GetMapping("/order/pur-order-print") //발주서프린트 페이지
    public void test2(){

    }

    @GetMapping("/order/pur-order-content/{business_number}/{purch_order_number}") //발주서 내용
    public String  purOrderContent(@PathVariable String business_number, @PathVariable String purch_order_number, Model model){
        System.out.println(business_number+ purch_order_number);

        model.addAttribute("company_info", orderService.getCompInfo(business_number));
        model.addAttribute("po_info",orderService.getPOinfo(purch_order_number));
        model.addAttribute("po_items",orderService.getPublishedPOItems(purch_order_number));
        model.addAttribute("bsno", business_number);
        model.addAttribute("pono",purch_order_number);
        return "/order/pur-order-content";
    }

    @GetMapping("/order/progress-check")
    public void progressCheck(Model model, RegisterCriteria cri) {
        cri.setOffset((cri.getPageNum()-1)*cri.getAmount());
        int total = orderService.getTotalPurOrder(cri);

        LocalDate startDate = cri.getParsedStartDate();
        LocalDate endDate = cri.getParsedEndDate();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String today = dtf.format(LocalDate.now());
        String endDate1=dtf.format(startDate);

        if(today.equals(endDate1))  {
            startDate = LocalDate.now().minusMonths(1);
            endDate = LocalDate.now();
            cri.setStartDate(String.valueOf(startDate));
            cri.setEndDate(String.valueOf(endDate));
        }



        System.out.println("startDate : "+startDate);
        System.out.println("endDate : " + endDate);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("table1List", orderService.getListforTable1(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, total));
    }

    @PostMapping("/order/showCheckPlanDetails")
    @ResponseBody
    public List<ProgressCheckDTO> showCheckPlansDetail(@RequestParam("proc_plan_number") String proc_plan_number,
                                                       @RequestParam("purch_order_number") String purch_order_number)   {

        System.out.println("전송된 조달계획번호 : " + proc_plan_number);
        System.out.println("전송된 발주번호 : " + purch_order_number);

        List<ProgressCheckDTO> ProgressCheckList = orderService.getListforProgressCheck(proc_plan_number, purch_order_number);
        System.out.println(ProgressCheckList);
        System.out.println("반환된 데이터 개수: " + ProgressCheckList.size());
        ProgressCheckList.forEach(x->{
            x.setProc_plan_number(proc_plan_number);
        });
        return ProgressCheckList;
    }

    @PostMapping("/order/checkPlanDB")
    @ResponseBody
    public int checkPlanDB(@RequestParam("proc_plan_number") String proc_plan_number)   {
        System.out.println("전송된 조달계획번호 : " + proc_plan_number);
        return orderService.checkPlanDB(proc_plan_number);
    }

    @PostMapping("/order/getCompany")
    @ResponseBody
    public Map<String, Object> getCompany(@RequestParam("proc_plan_number") String proc_plan_number) {
        System.out.println("전송된 조달계획번호 : " + proc_plan_number);

        Map<String, Object> response = new HashMap<>();
        List<getCompanyDTO> companyData = orderService.getCompany(proc_plan_number);

        if (companyData != null && !companyData.isEmpty()) {
            response.put("success", true);
            response.put("companyData", companyData);
        } else {
            response.put("success", false);
            response.put("message", "No data found for the given item code.");
        }

        return response;
    }

    @PostMapping("/order/inspectCheckPlans")
    @ResponseBody
    public int inspectCheckPlans(@RequestParam("purch_order_number_for_Inspect") String purch_order_number) {
        System.out.println("전송된 발주번호 : " + purch_order_number);
        return orderService.inspectCheckPlans(purch_order_number);
    }

    @PostMapping("/order/inputCheckPlans")
    public String inputCheckPlans(@RequestParam("Make_purch_order_number") String purch_order_number,
                                  @RequestParam("Make_proc_check_order[]") List<Integer> proc_check_order,
                                  @RequestParam("Make_proc_check_date[]") List<String> proc_check_date,
                                  @RequestParam("proc_plan_number") String proc_plan_number,
                                  Model model)  {
        System.out.println("purch_order_number : " + purch_order_number);
        System.out.println("proc_check_order : " + proc_check_order);
        System.out.println("proc_check_date : " + proc_check_date);
        System.out.println("proc_plan_number : " + proc_plan_number);

        ProgressCheckDTO dto = new ProgressCheckDTO();


        for(int i = 0; i < proc_check_order.size(); i++) {
            dto.setPurch_order_number(purch_order_number);
            dto.setProc_plan_number(proc_plan_number);
            dto.setProc_check_order(proc_check_order.get(i));
            dto.setProc_check_date(proc_check_date.get(i));
            orderService.saveProgressCheck(dto);
            System.out.println("결과 : "+purch_order_number) ;
        }

        return "redirect:/order/progress-check";
    }

    @PostMapping("/order/updateCheckPlan")
    public String updateCheckPlan(@RequestParam("Complete_proc_check_order") Integer proc_check_order,
                                  @RequestParam("Complete_proc_check_date") String proc_check_date,
                                  @RequestParam("Complete_completed_quantity[]") List<Integer> completed_quantity,
                                  @RequestParam("Complete_supplementation[]") List<String> supplementation,
                                  @RequestParam("Complete_prog_check_result[]") List<String> prog_check_result,
                                  @RequestParam("proc_plan_number") String proc_plan_number,
                                  @RequestParam("purch_order_number") String purch_order_number,
                                  Model model)  {

        System.out.println("proc_check_order : " + proc_check_order);
        System.out.println("proc_check_date  : " + proc_check_date);
        System.out.println("completed_quantity : " + completed_quantity);
        System.out.println("supplementation : " + supplementation);
        System.out.println("prog_check_result : " + prog_check_result);
        System.out.println("proc_plan_number : " + proc_plan_number);
        System.out.println("purch_order_number : " + purch_order_number);

        ProgressCheckDTO dto = new ProgressCheckDTO();

        for(int i = 0; i < completed_quantity.size(); i++) {
            dto.setProc_check_order(proc_check_order);
            dto.setProc_check_date(proc_check_date);
            dto.setCompleted_quantity(completed_quantity.get(i));
            dto.setSupplementation(supplementation.get(i));
            dto.setProg_check_result(prog_check_result.get(i));
            dto.setProc_plan_number(proc_plan_number);
            dto.setPurch_order_number(purch_order_number);
            orderService.updateProgressCheck(dto);
            System.out.println("결과 : "+proc_check_order) ;
        }

        return "redirect:/order/progress-check";
    }


    @GetMapping("/order/pur-order-report")
    public void purorderreport(Model model, RegisterCriteria cri)  {
        LocalDate startDate = cri.getParsedStartDate();
        LocalDate endDate = cri.getParsedEndDate();
        System.out.println("startDate : "+startDate);
        System.out.println("endDate : " + endDate);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String today = dtf.format(LocalDate.now());
        String endDate1=dtf.format(startDate);

        if(today.equals(endDate1))  {
            startDate = LocalDate.now().minusMonths(1);
            endDate = LocalDate.now();
            cri.setStartDate(String.valueOf(startDate));
            cri.setEndDate(String.valueOf(endDate));
        }


        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("CountProcPlan", orderService.getCountProcPlan(cri));
        model.addAttribute("CountPublishedPurOrder", orderService.getCountPublishedPurOrder(cri));
        model.addAttribute("CountProgCheckingProcPlan", orderService.getCountProgCheckingProcPlan(cri));
        model.addAttribute("FinishedProcPlan", orderService.getCountFinishedProcPlan(cri));
    }






}



