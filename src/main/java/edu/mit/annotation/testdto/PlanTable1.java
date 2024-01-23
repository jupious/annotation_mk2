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
public class PlanTable1 {
    private String product_code;
    private String product_name;
    private String item_code;
    private String item_name;
    private Integer item_required_quantity;
    private Integer proc_quantity;
    private Date prod_start_date;
    private Date proc_duedate;
    private float avg_return_rate;
}
