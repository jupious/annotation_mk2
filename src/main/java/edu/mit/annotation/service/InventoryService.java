package edu.mit.annotation.service;

import edu.mit.annotation.realdto.*;

import java.util.List;

public interface InventoryService {

    //조달계획이 있는 품목리스트 검색 및 페이징
    ListWithPaging<ReceiveItemDTO> searchReceiveItemList(Criteria cri);

    //(입고) 품목 선택시 해당발주에서 기입고 수량 검색
    Long getReceiveHistory(ReceiveHistorySearchDTO dto);

    //(입고) 입력한 품목 저장
    Integer saveReceivedItem(ItemSaveDTO dto);

    //(입고) 조달계획 마감처리
    Integer closingProcPlan(String proc_plan_number);

    //(입고) 검색 자동완성
    List<ReceiveItemDTO> autoReceiveSearch(Criteria cri);

    //여기까지입고처리/////////////////////////////////////////////

    //(명세서발행)마감된 조달계획 리스트
    ListWithPaging<ClosedProcPlanDTO> getClosedPrc(Criteria cri);

    //(명세서발행)조달계획검색 자동완성
    List<ClosedProcPlanDTO> autoPrcpSearch(Criteria cri);
    //거래명세서 미리보기
    StatementPreviewDTO getStatement(String proc_plan_number, String business_number);


    //출고품목 리스트
    ListWithPaging<ReleasingDTO> getReleaseData(Criteria cri);

    //출고 검색 자동완성
    List<ReleasingDTO> autoSearchRelease(Criteria cri);

    //출고내용 저장
    Integer saveReleaseItem(ReleaseItemDTO dto);

    //재고산출
    ListWithPaging<InventoryCalcDTO> getInvCalcData(Criteria cri);

    //재고산출 검색 자동완성
    List<InventoryCalcDTO> autoSearchInvCalc(Criteria cri);
    //재고금액 리포트
    List<InventoryReportDTO> getInvReport(Criteria cri);
}
