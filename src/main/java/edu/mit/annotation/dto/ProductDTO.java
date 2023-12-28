package edu.mit.annotation.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    //제품코드
    private String productCode;
    //제품명
    private String productName;
    //구성품목 리스트
    private List<ProductItemDTO> productItemList;
}
