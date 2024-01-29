package edu.mit.annotation.testdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProgressCheckDTO {
    private String purch_order_number;
    private Integer proc_check_order;
    private String prog_check_result;
    private String supplementation;
    private Integer completed_quantity;
    private String proc_check_date;
    private String proc_plan_number;

}
