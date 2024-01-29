package edu.mit.annotation.testdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CoopDTO {
    private  String business_number;
    private String company_name;
    private String company_address;
    private String manager;
    private String manager_tel;
    private String manager_email;
    private Long company_account;
}
