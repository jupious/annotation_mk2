package edu.mit.annotation.dto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcurementPlanDTO {
    //조달계획번호
    private String prcCode;
    //조달수량
    private String prcQuantity;
    //품목코드
    private String itemCode;
    //조달납기일
    private Date prcDueDate;
    //진행단계 (0:조달미예정 1:조달준비완료 2:조달진행중 3:조달완료)
    private Integer prcProgress;
    //평균반품률
    private Float avgReturnRate;
}
