package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class AutoProgPlanAlertDTO {
    private String purch_order_number;
    private Date proc_check_date;
    private String item_code;
    private Integer purch_order_quantity;
    private String item_name;
    private String business_number;
    private String company_name;
    private String company_address;
    private String manger;
    private String manager_tel;
    private Integer proc_check_order;
}
