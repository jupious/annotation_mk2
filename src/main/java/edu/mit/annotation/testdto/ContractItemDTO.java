package edu.mit.annotation.testdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractItemDTO {
    private String cCode;
    private String itemCode;
    private String itemName;
    private String compName;
    private String compNum;
    private Integer price;
    private Integer leadTime;
    private boolean contDone;

}
