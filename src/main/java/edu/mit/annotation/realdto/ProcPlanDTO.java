package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcPlanDTO {
    private String proc_plan_number;
    private String item_code;
    private Integer proc_quantity;
    private Date proc_duedate;
    private Boolean proc_progress;

}
