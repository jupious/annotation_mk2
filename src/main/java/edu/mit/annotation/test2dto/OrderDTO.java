package edu.mit.annotation.test2dto;

import lombok.*;

import java.util.Date;
import java.util.List;

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
    private String delDate;

    //사업자번호
    private String businessNum;

    //담당자
    private String eName;

    //검수 리스트
    private List<CheckDTO> checkDTOs;

    //아이템 리스트
    private List<TestItemDTO> itemDTOs;


}
