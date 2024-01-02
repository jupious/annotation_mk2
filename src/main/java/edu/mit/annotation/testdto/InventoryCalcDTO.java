package edu.mit.annotation.testdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryCalcDTO {
    private String itemCode;
    private String itemName;
    private String size;
    private String material;
    private Integer originQuantity;
    private Integer receiveQuantity;
    private Integer releaseQuantity;
    private Integer calculatedQuantity;
}
