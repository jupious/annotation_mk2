package edu.mit.annotation.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdCodeWithNameDTO {

    private String product_name;
    private String product_code;

}
