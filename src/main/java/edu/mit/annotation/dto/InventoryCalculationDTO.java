package edu.mit.annotation.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryCalculationDTO {
    //품목코드
    private String itemCode;
    //기본재고
    private Integer originalQuantity;
    //입고수량
    private Integer receivedQuantity;
    //출고수량
    private Integer releasedQuantity;
    //산출재고
    private Integer calculatedQuantity;
}
