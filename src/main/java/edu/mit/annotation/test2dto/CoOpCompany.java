package edu.mit.annotation.test2dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoOpCompany { // 협력회사
    //사업자번호
    private String business_number;
    //업체명
    private String company_name;
    //주소
    private String company_address;
    //담당자
    private String manager;
    //연락처
    private String manager_tel;
    //이메일
    private String manager_email;
    //입금계좌
    private String company_account;
}
