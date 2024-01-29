package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPurchaseOrderDTO {
    private String purch_order_number;
    private Date purch_order_date;
    private String purch_order_detail;
    private List<NewPurchOrderItem> newPurchOrderItem;
}
