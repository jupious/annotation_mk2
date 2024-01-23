package edu.mit.annotation.test2dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CheckInfo {
    //발주번호
    private String purch_order_number;
    //업체명
    private String company_name;
    //사업자번호.협력회사
    private String business_number;
    //품목코드.품목
    private String item_code;
    //품목명.품목
    private String item_name;
    //발주수량.발주내용
    private Integer purch_order_quantity;

    private List<ProgCheck> manyProg;
}
