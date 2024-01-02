package edu.mit.annotation.testdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReleaseItemDTO {
    private String itemCode;
    private String itemName;
    private Integer invQuantity;
    private Integer releaseQuantity;
}
