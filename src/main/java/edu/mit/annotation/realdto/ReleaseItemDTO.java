package edu.mit.annotation.realdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReleaseItemDTO {
    private String item_code;
    private Integer release_quantity;
}
