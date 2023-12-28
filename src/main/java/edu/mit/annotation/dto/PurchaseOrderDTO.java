package edu.mit.annotation.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderDTO {
    //발주번호
    private String poCode;
    //발주일
    private Date poDate;
    //발주납기일
    private Date poDueDate;
    //마감여부
    private Boolean isCompleted;
    //발주내용리스트
    private List<PurchaseOrderDetailDTO> purchaseOrderDetailList;
    //사업자번호
    private String businessNumber;

}
