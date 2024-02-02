package edu.mit.annotation.mapper;

import edu.mit.annotation.realdto.*;
import edu.mit.annotation.testdto.ProgressCheckDTO;
import edu.mit.annotation.testdto.PurchaseOrderListDTO;
import edu.mit.annotation.testdto.RegisterCriteria;
import edu.mit.annotation.testdto.getCompanyDTO;

import java.util.List;

public interface OrderMapper {
    //검색결과 총 갯수
    Long getSearchDataCount();

    //미발주 조달계획 리스트
    List<ProcPlanNoPO> getProcPlanList(Criteria cri);

    //미발주 조달계획검색 자동완성
    List<ProcPlanNoPO> autoSearchPrcp(Criteria cri);

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

    //발행된 발주리스트 가져오기
    List<PublishedPurchaseOrderDTO> getPoList(Criteria cri);

    //발행된 발주리스트 검색 자동완성
    List<PublishedPurchaseOrderDTO> autoSearchPOList(Criteria cri);

    //발주품목 삭제
    void deletePurchaseOrderItem(String purch_order_number);
    //발주서 삭제
    void deletePurchaseOrder(String purch_order_number);

    //발주품목 정보
    List<PublishedPOItemDTO> getPOitems(String purch_order_number);

    //발주서 정보
    PublishedPODTO getPOInfo(String purch_order_number);

    //이메일 자동완성
    List<CompanyInfoDTO> getCompInfoWithEmail(String email);

    //발주서의 조달계획 마감여부
    Integer isPrcpClosed(String purch_ordre_number);

    //진척검수 3일안에 있는일정확인
    List<AutoProgPlanAlertDTO> getAutoProcPlan(Criteria cri);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 아래부터 나중에 이어붙일 부분

    // 진척검수 관련
    List<PurchaseOrderListDTO> getListforTable1(RegisterCriteria cri);

    List<ProgressCheckDTO> getListforProgressCheck(String proc_plan_number, String purch_order_number);
    void saveProgressCheck(ProgressCheckDTO dto);

    int inspectCheckPlans(String purch_order_plan);

    int getTotalPurOrder(RegisterCriteria cri);

    void updateProgressCheck(ProgressCheckDTO dto);

    List<getCompanyDTO> getCompany(String proc_plan_number);

    int checkPlanDB(String proc_plan_number);

    // 발주현황리포트 관련
    List<PurchaseOrderListDTO> getListPurOrder();
    int getCountListPurOrder();

    // 총 조달계획 갯수
    int getCountProcPlan(RegisterCriteria cri);

    // 발주서 발행 갯수
    int getCountPublishedPurOrder(RegisterCriteria cri);

    // 진척검수 진행중 갯수
    int getCountProgCheckingProcPlan(RegisterCriteria cri);

    // 조달마감 갯수
    int getCountFinishedProcPlan(RegisterCriteria cri);
}
