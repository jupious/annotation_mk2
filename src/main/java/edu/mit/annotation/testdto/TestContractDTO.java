package edu.mit.annotation.testdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TestContractDTO {
    private String contract_number; // 계약서 번호
    private String business_number; // 사업자 번호
    private Date contract_date; // 계약일자
    private String contract_save_name;  // 계약서 저장파일명
    private String contract_origin_name;    // 계약서 원본파일명
    private String note;    // 비고
}
