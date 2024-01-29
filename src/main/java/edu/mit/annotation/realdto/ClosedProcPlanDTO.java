package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClosedProcPlanDTO {
    private String proc_plan_number;
    private String company_name;
    private String business_number;
    private String item_code;
    private String item_name;
    private Date purch_order_date;
    private Date receive_duedate;
}
