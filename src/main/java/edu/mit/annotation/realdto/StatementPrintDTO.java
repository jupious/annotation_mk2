package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatementPrintDTO {
    private String purch_order_detail;
    private String business_number;
    private String company_name;
    private String company_address;
    private String manager;
    private String manager_tel;
    private Integer received_quantity;
    private Date proc_duedate;
    private Date purch_order_date;
    private String note;
    private String item_code;
    private String item_name;
    private Integer item_price;
}
