package edu.mit.annotation.test2dto;

import lombok.Data;

import java.util.Date;

@Data
public class CheckList {
    //발주번호
    private String purch_order_number;
    //품목코드
    private String item_code;
    //품목명
    private String item_name;
    //검수결과
    private  String prog_check_result;
    //제작수량
    private Integer completed_quantity;
    //입고예정일
    private Date receive_duedate;
}
