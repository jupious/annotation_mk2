package edu.mit.annotation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionPlan {
    // 계획코드
    private String prod_plan_code;
    // 제품코드
    private String product_code;
    // 생산시작
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date prod_start_date;
    // 생산종료
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date prod_end_date;
    // 생산수량
    private Integer prod_quantity;
    // 조달간격
    private Integer procure_term;
}
