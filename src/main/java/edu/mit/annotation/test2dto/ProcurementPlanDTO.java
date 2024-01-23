package edu.mit.annotation.test2dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProcurementPlanDTO { // 조달계획
    //조달계획번호
    private String proc_plan_number;
    //품목코드
    private String item_code;
    //조달수량
    private Integer proc_quantity;
    //조달납기일
    private Date proc_duedate;
    //진행단계
    private Integer proc_progress;
}
