package edu.mit.annotation.realdto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class InputCompItemDTO {
    private Integer item_required_quantity;
    private Double avg_return_rate;
    private String item_code;
    private String product_code;
}
