package edu.mit.annotation.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractDTO {
    //계약번호
    private String cCode;
    //계약일
    private Date cDate;
    //비고
    private String etc;
    //계약내용리스트
    private List<ContractDetailDTO> contractDetailList;
    //사업자번호
    private String businessNumber;

}
