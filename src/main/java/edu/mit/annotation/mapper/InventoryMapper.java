package edu.mit.annotation.mapper;

import edu.mit.annotation.realdto.*;

import java.util.List;

public interface InventoryMapper {
    //입고==============================================================
    //입고화면 검색쿼리
    List<ReceiveItemDTO> searchReceiveItemList(Criteria cri);

    //검색결과 총 갯수구하기
    Long getSearchDataCount();

    //입고항목 저장(마감x)
    Integer saveReceiveItem(ItemSaveDTO dto);

    //입고항목 마감
    Integer closingProcPlan(String proc_plan_number);
    Integer saveClosedProcPlan(String proc_plan_number);


    //해당품목의 기존 입고내역가져오기(해당품목 발주일~ 조달납기)까지 입고된 수량
    List<Long> getReceiveHistoryBefore(ReceiveHistorySearchDTO dto);

    //입고품목 검색 자동완성
    List<ReceiveItemDTO> autoReceiveSearch(Criteria cri);
    //입고여기까지=============================================================



    //거래명세서===============================================================
    //마감된 발주서번호 조회 -> 마감된 조달계획 조회
    List<ClosedProcPlanDTO> getClosedProcPlan(Criteria cri);

    List<ClosedProcPlanDTO> autoSearchClosedProcPlan(Criteria cri);
    //거래명세서여기까지========================================================

    //거래명세서 미리보기=======================================================

    //사업자번호로 회사정보조회
    CompanyInfoDTO getCompanyInfo(String business_number);
    List<StatementItemDTO> getStatementItems(String proc_plan_number);

    //거래명세서 미리보기 여기까지================================================


    //출고====================================================================
    //출고품목조회
    List<ReleasingDTO> getReleaseList(Criteria cri);
    //출고품목 자동완성
    List<ReleasingDTO> autoSearchRelease(Criteria cri);

    //출고내용 저장
    Integer saveReleaseItem(ReleaseItemDTO dto);
    //출고여기까지==============================================================

    //재고산출=================================================================
    List<InventoryCalcDTO> getInvCalcData(Criteria cri);
    //재고산출 자동완성
    List<InventoryCalcDTO> autoSearchInvCalc(Criteria cri);
    //재고산출여기까지===========================================================

    //재고금액리포트=============================================================
    List<InventoryReportDTO> getInvReport(Criteria cri);
    //재고금액리포트여기까지======================================================
}
