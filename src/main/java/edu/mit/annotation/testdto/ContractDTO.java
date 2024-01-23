package edu.mit.annotation.testdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO {
    private String contract_origin_name,contract_save_name,business_number,company_name,company_account,contract_number,note,item_code,item_name,details;
    private Integer item_price,lead_time;
}
