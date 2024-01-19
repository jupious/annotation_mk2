package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReleasingDTO {
    private String item_code;
    private String item_name;
    private String product_name;
    private Date prod_start_date;
    private Integer total_required_quantity;
    private Integer item_quantity;
}
