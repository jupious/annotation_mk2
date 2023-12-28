package edu.mit.annotation.service;

import edu.mit.annotation.dto.*;

import java.util.List;

public interface InventoryService {
    //입고 예정 발주 보여주기
    List<PurchaseOrderDTO> getPurchaseOrderList();
    //선택한 발주서의 품목정보
    List<ItemDTO> getPurchaseOrderItemList(List<String> prcCodeList);
    //선택한 발주서의 발주정보
    PurchaseOrderDTO getPurchaseOrder(String poCode);
    //선택한 발주서의 기입력정보

    //입력된 정품/반품수량 저장
    boolean saveReceivedItem(List<ReceiveDetailDTO> receiveDetailList);
    //마감처리
    boolean completePurchaseOrder(String poCode);
}
