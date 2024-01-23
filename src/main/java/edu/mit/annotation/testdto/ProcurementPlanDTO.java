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
public class ProcurementPlanDTO {
    private String proc_plan_number;
    private Integer proc_quantity;
    private Date proc_duedate;
    private boolean proc_progress;
    private String item_code;
}
