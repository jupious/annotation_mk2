package edu.mit.annotation.test2dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderList {
    //발주번호
    private String purch_order_number;
    //업체명
    private String company_name;
//    //품목코드
//    private List<String> item_codes;
//    //품목명
//    private List<String> item_names;
    //품목코드
    private String item_code;
    //품목명
    private String item_name;
    //입고예정일
    private Date receive_duedate;

//    public OrderList(String purch_order_number, String company_name,
//         List<String> item_codes, List<String> item_names, Date receive_duedate){
//        this.purch_order_number = purch_order_number;
//        this.company_name = company_name;
//        this.item_codes = item_codes;
//        this.item_names = item_names;
//        this.receive_duedate = receive_duedate;
//    }

    // 품목코드 리스트 반환 메서드
//    public List<String> getItemCodes() {
//        return item_codes;
//    }
//    // 품목명 리스트 반환 메서드
//    public List<String> getItemNames() {
//        return item_names;
//    }
}
