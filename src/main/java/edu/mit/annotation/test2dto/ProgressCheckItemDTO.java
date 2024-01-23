package edu.mit.annotation.test2dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressCheckItemDTO { // 검수품목
    //검수품목번호
    private Integer prog_check_item_number;
    //품목코드
    private String item_code;
    //검수번호
    private  Integer prog_check_number;
    //검수결과
    private  String prog_check_result;
    //제작수량
    private Integer completed_quantity;
    //보완사항
    private  String supplementation;
}
