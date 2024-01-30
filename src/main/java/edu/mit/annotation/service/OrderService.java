package edu.mit.annotation.service;

import edu.mit.annotation.realdto.*;
import edu.mit.annotation.testdto.ProgressCheckDTO;
import edu.mit.annotation.testdto.PurchaseOrderListDTO;
import edu.mit.annotation.testdto.RegisterCriteria;

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

    //발주서 목록
    ListWithPaging<PublishedPurchaseOrderDTO> getPublishedPOList(Criteria cri);

    //발주서 삭제
    void deletePurchaseOrder(String purch_order_number);

    //발주서 정보
    PublishedPODTO getPOinfo(String purch_order_number);

    //발주품목 정보
    List<PublishedPOItemDTO> getPublishedPOItems(String purch_order_number);

    //이메일 자동완성용 회사정보
    List<CompanyInfoDTO> getCompWithEmail(String email);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 아래부터 나중에 이어붙일 부분
    // 진척검수 관련
    List<PurchaseOrderListDTO> getListforTable1();

    List<ProgressCheckDTO> getListforProgressCheck(String proc_plan_number, String purch_order_number);
    void saveProgressCheck(ProgressCheckDTO dto);
    int getTotalPurOrder(RegisterCriteria cri);

    int inspectCheckPlans(String purch_order_number);

    void updateProgressCheck(ProgressCheckDTO dto);

    int CheckProgDB(String proc_plan_number);


    // 발주현황리포트 관련
    List<PurchaseOrderListDTO> getListPurOrder();
    int getCountListPurOrder();

    int getCountProcPlan();

    int getCountPublishedPurOrder();

    int getCountProgCheckingProcPlan();

    int getCountFinishedProcPlan();

}
