package edu.mit.annotation.testdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TestContractItemDTO {
    private String item_code;   // 품목코드
    private String contract_number; // 계약서번호
    private Integer lead_time;  // 리드타임
    private Integer item_price; // 단가
    private String details; // 세부사항
}
