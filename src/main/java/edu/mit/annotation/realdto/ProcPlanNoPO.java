package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcPlanNoPO {
    private String proc_plan_number;
    private String business_number;
    private String company_name;
    private String item_name;
    private Date proc_duedate;
}
