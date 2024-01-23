package edu.mit.annotation.testdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductionPlanDTO {
    private Date prod_start_date;
    private Date prod_end_date;
    private String product_code;
    private String product_name;
    private Integer prod_quantity;
    private String item_code;
    private String item_name;
    private Integer item_required_quantity;
    private float avg_return_rate;
    private Integer procure_term;
}
