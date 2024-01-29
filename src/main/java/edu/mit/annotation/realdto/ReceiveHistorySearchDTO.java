package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiveHistorySearchDTO {
    private String proc_plan_number;
    private Date startDate;
    private Date endDate;

}
