package edu.mit.annotation.realdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryCalcDTO {
    private String item_code;
    private String item_name;
    private Integer width;
    private Integer length;
    private Integer height;
    private String material;
    private Long prime_quantity;
    private Long received_in_date;
    private Long released_in_date;
    private Integer item_price_nonull;
}
