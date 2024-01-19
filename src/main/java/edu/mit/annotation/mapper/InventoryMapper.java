package edu.mit.annotation.mapper;

import edu.mit.annotation.realdto.*;

import java.util.Date;
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
    //조달계획 번호로 발주번호 조회
    String findPurchaseOrder(String proc_plan_number);
    //발주번호로 해당 발주서에 등록된 조달계획들의 마감여부확인
    List<Integer> getProcPlanCloingStatus(String purch_order_number);

    //발주서 마감
    Integer closePurchaseOrder(String purch_order_number);

    //해당품목의 기존 입고내역가져오기(해당품목 발주일~ 조달납기)까지 입고된 수량
    List<Long> getReceiveHistoryBefore(SearchDTO dto);
    //입고여기까지=============================================================



    //거래명세서===============================================================
    //마감된 발주서번호 조회
    List<ClosedPurchaseOrderDTO> searchWithPONumber(Criteria cri);

    //발주번호로 품목명, 업체명 추가조회
    List<PurchOrderItemWithCompanyName> getItemCompanyName(String purch_order_number);

    //업체명으로 발주번호 조회
    List<ClosedPurchaseOrderDTO> searchWithCompanyName(Criteria cri);
    //품목명으로 발주번호 조회
    List<ClosedPurchaseOrderDTO> searchWithItemName(Criteria cri);


    //거래명세서여기까지========================================================

    //거래명세서 미리보기=======================================================
    List<StatementPrintDTO> getStatementInfo(String purch_order_number);
    void statementPbCntUp(String purch_order_number);

    //거래명세서 미리보기 여기까지================================================


    //출고====================================================================
    //출고품목죄회
    List<ReleasingDTO> getReleaseList(Criteria cri);
    //출고내용 저장
    Integer saveReleaseItem(ReleaseItemDTO dto);
    //출고여기까지==============================================================
}
