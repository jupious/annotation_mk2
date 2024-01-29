package edu.mit.annotation.testdto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDTO {

    private String item_code;
    private String item_name;
    private String unit_code;
    private String assy_code;
    private String part_code;
    private Float width;
    private Float length;
    private Float height;
    private String material;
    private String blueprint_origin_name;
    private String blueprint_save_name;
    private MultipartFile blueprint;

}
