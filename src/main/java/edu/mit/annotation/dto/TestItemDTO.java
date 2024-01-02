package edu.mit.annotation.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestItemDTO {

    private String itemCode;
    private String itemName;
    private String unitCode;
    private String assyCode;
    private String partCode;
    private Float width;
    private Float length;
    private Float height;
    private String material;

}
