package edu.mit.annotation.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryAmountDTO {
    //분류명
    private String classificationName;
    //재고금액
    private Integer inventoryAmount;
}
