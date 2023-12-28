package edu.mit.annotation.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {
    //품목코드
    private String itemCode;
    //품목명
    private String itemName;
    //대분류코드
    private String unitCode;
    //중분류코드
    private String assyCode;
    //소분류코드
    private String partCode;
    //가로
    private Float width;
    //세로
    private Float length;
    //높이
    private Float height;
    //재질
    private String material;
    //도면
    private String blueprint;
    //재고수량
    private Integer invQuantity;
}
