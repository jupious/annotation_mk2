package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClosedPurchaseOrderDTO {
    private String purch_order_number;
    private String company_name;
    private String item_name_string;
    private Date purch_order_date;
    private Date receive_duedate;
    private Integer statement_publish_count;
}
