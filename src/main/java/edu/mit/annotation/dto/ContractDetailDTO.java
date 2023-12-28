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
    private Integer LeadTime;
    //세부내용
    private String detail;
}
