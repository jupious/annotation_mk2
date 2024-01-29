package edu.mit.annotation.realdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishedPOItemDTO {
    private String item_code;
    private String item_name;
    private Integer width;
    private Integer height;
    private Integer length;
    private String material;
    private Integer purch_order_quantity;
    private Integer item_price;
    private String note;

}
