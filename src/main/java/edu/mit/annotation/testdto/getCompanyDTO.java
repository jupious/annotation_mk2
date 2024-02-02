package edu.mit.annotation.testdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class getCompanyDTO {
    private String contract_number,company_name,item_code,item_name,business_number,details,note,company_account, contract_origin_name,contract_save_name, proc_plan_number, purch_order_number;
    private Integer item_price,lead_time;
}
