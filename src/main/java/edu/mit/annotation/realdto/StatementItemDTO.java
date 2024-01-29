package edu.mit.annotation.realdto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatementItemDTO {
    private String item_code;
    private String item_name;
    private Integer receive_quantity;
    private Integer item_price;
    private Integer prod_price;
    private String note;
}
