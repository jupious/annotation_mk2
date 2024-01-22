package edu.mit.annotation.realdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryReportDTO {
    private String type_name;
    private Long inv_amount;

}
