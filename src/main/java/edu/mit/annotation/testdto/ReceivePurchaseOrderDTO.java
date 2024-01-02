package edu.mit.annotation.testdto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceivePurchaseOrderDTO {
    //입고 예정 발주 임시

    //발주번호
     private String poCode;
    //협력회사명
    private String companyName;
    //품목명 리스트
    private List<String> itemNameList;
    //발주일
    private Date poDate;
    //발주납기
    private Date poDueDate;
    //품목리스트
    private List<ReceiveItemDTO> receiveItemList;
    //협력사 정보
    private CoopCompDTO compInfo;
}
