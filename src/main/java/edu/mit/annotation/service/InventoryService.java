package edu.mit.annotation.service;

import edu.mit.annotation.realdto.*;

import java.util.List;

public interface InventoryService {

    //조달계획이 있는 품목리스트 검색 및 페이징
    ListWithPaging<ReceiveItemDTO> searchReceiveItemList(Criteria cri);

    //(입고) 품목 선택시 해당발주에서 기입고 수량 검색
    Long getReceiveHistory(SearchDTO dto);

    //(입고) 입력한 품목 저장
    Integer saveReceivedItem(ItemSaveDTO dto);

    //(입고) 조달계획 마감처리
    Integer closingProcPlan(String proc_plan_number);
    //발주서 마감처리
    void closingPO(String proc_plan_number);
    //여기까지입고처리

    //마감된 발주서 리스트
    ListWithPaging<ClosedPurchaseOrderDTO> getClosedPO(Criteria cri);

    //거래명세서 미리보기
    StatementPreviewDTO getStatement(String purch_order_number);

    //거래명세서 발행횟수증가
    void stmtPbCntUp(String purch_order_number);

    //출고품목 리스트
    ListWithPaging<ReleasingDTO> getReleaseData(Criteria cri);

    //출고내용 저장
    Integer saveReleaseItem(ReleaseItemDTO dto);

}
