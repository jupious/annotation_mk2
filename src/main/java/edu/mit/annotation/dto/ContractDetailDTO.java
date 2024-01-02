package edu.mit.annotation.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDetailDTO {
    //계약서번호
    private String cCode;
    //품목코드
    private String itemCode;
    //L/T
    private Integer leadTime;
    //단가
    private Integer price;
    //세부내용
    private String detail;
}
