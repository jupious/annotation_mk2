package edu.mit.annotation.realdto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {
    private String item_code;
    private String item_name;
    private Integer width;
    private Integer length;
    private Integer height;
    private String material;
    private String blueprint_save_name;
    private String blueprint_origin_name;
    private String unit_code;
    private String assy_code;
    private String part_code;

}
