package edu.mit.annotation.test2dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderItemDTO { // 발주내용
    //발주번호
    private String purch_order_number;
    //조달계획번호
    private String proc_plan_number;
    //발주수량
    private Integer purch_order_quantity;
    //비고
    private String note;
}
