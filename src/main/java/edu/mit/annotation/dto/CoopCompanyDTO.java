package edu.mit.annotation.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoopCompanyDTO {
    //사업자번호
    private String businessNumber;
    //업체명
    private String companyName;
    //담당자
    private String manager;
    //연락처
    private String managerTel;
    //이메일
    private String managerEmail;
    //입금계좌
    private String accountNumber;
}
