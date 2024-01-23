package edu.mit.annotation.test2dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO { // 품목
    //품목코드
    private String item_code;
    //품목명
    private String item_name;
}
