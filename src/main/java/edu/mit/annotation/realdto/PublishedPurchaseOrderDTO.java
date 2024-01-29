package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishedPurchaseOrderDTO {
    private String purch_order_number;
    private String item_names;
    private String company_name;
    private Date purch_order_date;
    private String business_number;

}
