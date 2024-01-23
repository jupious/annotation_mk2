package edu.mit.annotation.test2dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    //발주번호.발주
    private String purch_order_number;
    //발주일.발주
    private Date purch_order_date;
    //입고예정일.발주
    private Date receive_duedate;
    //업체명.협력회사
    private String company_name;
    //사업자번호.협력회사
    private String business_number;
    //담당자.협력회사
    private String manager;
    //품목코드.품목
    private String item_code;
    //품목명.품목
    private String item_name;
    //발주수량.발주내용
    private Integer purch_order_quantity;

//    //검수번호.진척검수
//    private Integer prog_check_number;
//    //차수.진척검수
//    private Integer prog_check_order;
//    //검수일정,진척검수
//    private Date prog_check_date;

}
