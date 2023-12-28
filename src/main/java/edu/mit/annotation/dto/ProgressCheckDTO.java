package edu.mit.annotation.dto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgressCheckDTO {
    //발주번호
    private String poCode;
    //검수번호
    private String progressCheckCode;
    //차수
    private Integer checkNumber;
    //검수일정
    private Date checkDate;
    //검수결과
    private String checkResult;
    //제작수량합계
    private Integer prodCompleteQuantity;
    //보완사항
    private String supplementation;
}
