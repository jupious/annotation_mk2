package edu.mit.annotation.realdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemSaveDTO {
    private String proc_plan_number;
    private Integer received_quantity;
}
