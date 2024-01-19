package edu.mit.annotation.realdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemSaveDTO {
    private String item_code;
    private Integer received_quantity;
}
