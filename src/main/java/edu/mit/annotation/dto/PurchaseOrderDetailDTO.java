package edu.mit.annotation.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderDetailDTO {
    //발주서번호
    private String poCode;
    //조달계획번호
    private String prcCode;
    //발주수량
    private Integer orderQuantity;
    //비고
    private String etc;
}
