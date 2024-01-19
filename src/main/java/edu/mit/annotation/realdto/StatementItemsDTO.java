package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatementItemsDTO {
    private Long received_quantity;
    private String note;
    private String item_code;
    private String item_name;
    private Integer item_price;
    private Integer prod_price;
}
