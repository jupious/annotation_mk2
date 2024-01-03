package edu.mit.annotation.testdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReleaseItemDTO {
    private String itemCode;
    private String itemName;
    private Date prodDate;
    private Integer needQuantity;
    private Integer invQuantity;
    private Integer releaseQuantity;
    private String product;
}
