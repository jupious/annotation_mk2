package edu.mit.annotation.testdto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDTO {
    //발주번호
    private String poCode;

    //업체명
    private String comName;

    //품목명
    private  String pName;

    //검수진행차수
    private String resultnum;

    //진척도
    private Integer progress;

    //발주일
    private String orderDate;

    //발주납기일
    private String deldate;

    //사업자번호
    private String businessNum;

    //담당자
    private String eName;


}
