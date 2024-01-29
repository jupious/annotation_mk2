package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyInfoDTO {
    private String company_name;
    private String business_number;
    private String manager;
    private String manager_tel;
    private String company_address;
    private String manager_email;
}
