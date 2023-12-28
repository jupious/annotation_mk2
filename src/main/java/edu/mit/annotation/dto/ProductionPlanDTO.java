package edu.mit.annotation.dto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductionPlanDTO {
    //계획번호
    private String productionCode;
    //제품코드
    private String productCode;
    //생산시작일
    private Date startDate;
    //생산종료일
    private Date endDate;
    //생산수량
    private Integer productionQuantity;

}
