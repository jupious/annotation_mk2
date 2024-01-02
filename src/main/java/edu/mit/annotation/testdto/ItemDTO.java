package edu.mit.annotation.testdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {
    //순번
    private Integer num;

    //품목코드
    private String iCode;

    //품목이름
    private String iName;

    //수량
    private Integer count;

    //단가
    private Integer unitPrice;

    //공급가격
    private  Integer supPrice;

}
