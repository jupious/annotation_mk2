package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchOrderItemsDTO {
    private String item_code;
    private String item_name;
    private Integer width;
    private Integer length;
    private Integer height;
    private String material;
    private Integer proc_quantity;
    private Integer item_price;
    private Date proc_duedate;
    private String proc_plan_number;

}
