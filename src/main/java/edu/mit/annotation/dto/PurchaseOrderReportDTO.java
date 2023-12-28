package edu.mit.annotation.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderReportDTO {
    //진행중 발주서 숫자
    private Integer progressCount;
    //마감된 발주서 숫자
    private Integer completedCount;
}
