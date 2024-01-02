package edu.mit.annotation.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestContractDTO {
    // 사업자번호
    private String businessNumber;
    // 업체명
    private String companyName;
    // 계좌정보
    private String accountNumber;
    // 계약번호
    private String cCode;
    // 비고
    private String etc;
}
