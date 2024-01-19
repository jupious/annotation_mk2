package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiveItemDTO {
    private String item_code;
    private String item_name;
    private String material;
    private Date purch_order_date;
    private Date proc_duedate;
    private Integer purch_order_quantity;
    private String proc_plan_number;
}
