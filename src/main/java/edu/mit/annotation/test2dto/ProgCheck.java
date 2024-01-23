package edu.mit.annotation.test2dto;

import lombok.Data;

import java.util.Date;
@Data
public class ProgCheck {
    //발주번호
    private String purch_order_number;
    //차수
    private Integer prog_check_order;
    //검수일정
    private Date prog_check_date;
    //검수결과
    private  String prog_check_result;
    //제작수량
    private Integer completed_quantity;
    //보완사항
    private  String supplementation;

}

