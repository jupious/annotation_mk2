package edu.mit.annotation.test2dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgressCheckDTO { // 진척검수
    //검수번호
    private Integer prog_check_number;
    //발주번호
    private String purch_order_number;
    //차수
    private Integer prog_check_order;
    //검수일정
    private Date prog_check_date;



}
