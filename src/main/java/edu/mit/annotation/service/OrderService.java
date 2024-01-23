package edu.mit.annotation.service;

import edu.mit.annotation.dto.ProcurementPlanDTO;
import edu.mit.annotation.dto.PurchaseOrderDTO;
import edu.mit.annotation.realdto.*;

import java.util.List;

public interface OrderService {
    //미발주 조달계획리스트
    ListWithPaging<ProcPlanNoPO> getProcPlanListWithNoPO(Criteria cri);

    //발주 회사정보
    CompanyInfoDTO getCompInfo(String business_number);
    //발주품목정보
    List<PurchOrderItemsDTO> getPOItems(String prcpNumbers);

    //발주서 저장
    Integer savePurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO);




}
