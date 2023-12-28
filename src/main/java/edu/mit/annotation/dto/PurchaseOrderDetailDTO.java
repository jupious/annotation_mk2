package edu.mit.annotation.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderDetailDTO {
    //조달계획번호
    private String prcCode;
    //발주수량
    private String orderQuantity;
    //비고
    private String etc;
}
