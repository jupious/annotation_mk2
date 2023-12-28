package edu.mit.annotation.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductItemDTO {
    //품목코드
    private String itemCode;
    //품목수량
    private Integer itemQuantity;
}
