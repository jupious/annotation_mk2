package edu.mit.annotation.testdto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeDTO {
    private String unit_code;
    private String unit_name;
    private String assy_code;
    private String assy_name;
    private String part_code;
    private String part_name;
    private String item_code;
}
