package edu.mit.annotation.testdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderListDTO {
    private String purch_order_number;
    private String purch_order_date;
    private String purch_order_detail;
    private String purch_order_quantity;
    private String note;
    private String receive_duedate;
    private String item_code;
    private String item_name;
    private String business_number;
    private String company_name;
    private String proc_plan_number;
    private String prog_check_results;
    private Integer prog_check_total;
    private Integer prog_check_count;
}
