package edu.mit.annotation.testdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractItemDTO {
    private String item_code;
    private String item_name;
    private Integer item_price;
    private Integer lead_time;
    private String details;
    private String contract_number;

}
