package edu.mit.annotation.test2dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO { // 발주
    //발주번호
    private String purch_order_number;
    //발주일
    private Date purch_order_date;
    //입고예정일
    private Date receive_duedate;
    //마감여부
    private  Integer closing_status;
    //명세서발행횟수
    private Integer statement_publish_count;
    //특이사항
    private String purch_order_detail;



}
