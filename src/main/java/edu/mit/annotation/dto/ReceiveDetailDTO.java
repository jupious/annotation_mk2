package edu.mit.annotation.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiveDetailDTO {
    //입고번호
    private Integer receiveNum;
    //품목코드
    private String itemCode;
    //입고일
    private Date receiveDate;
    //정품수량
    private Integer fairQuantity;
    //반품수량
    private Integer returnQuantity;
}
