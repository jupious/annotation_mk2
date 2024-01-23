package edu.mit.annotation.mapper;

import edu.mit.annotation.realdto.*;

import java.util.List;

public interface OrderMapper {
    //검색결과 총 갯수
    Long getSearchDataCount();

    //미발주 조달계획 리스트
    List<ProcPlanNoPO> getProcPlanList(Criteria cri);

    //발주서 회사정보
    CompanyInfoDTO getCompanyInfo(String business_number);
    //발주서 품목정보
    List<PurchOrderItemsDTO> getPurchOrderItemList(String prcpNumbers);

    //발주서 저장
    void savePurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO);
    //발주내용 저장
    void savePurchaseOrderItem(NewPurchOrderItem newPurchOrderItem);

    //발주번호가 가장 큰값 가져오기
    String getLatestPurchOrderNumber();


}
