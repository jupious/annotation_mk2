package edu.mit.annotation.testdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiveItemDTO {
    //입고 예정 항목 임시

    //품목코드
    private String itemCode;
    //품목명
    private String itemName;
    //발주수량
    private Integer poItemQuantity;
    //정품수량
    private Integer fairQuantity;
    //반품수량
    private Integer returnQuantity;
    //단가
    private Integer price;
    //공급가액
    private Integer itemTotalPrice;
    //비고
    private String etc;
}
