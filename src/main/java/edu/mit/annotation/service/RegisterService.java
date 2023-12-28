package edu.mit.annotation.service;


import edu.mit.annotation.dto.ContractDTO;
import edu.mit.annotation.dto.ItemDTO;
import edu.mit.annotation.dto.ProcurementPlanDTO;

import java.util.List;

public interface RegisterService {
    // 품목등록
    String registerItem(ItemDTO itemDTO);
    // 품목 리스트 보기
    List<ItemDTO> getListItem();
    // 품목 수정
    boolean modifyItem(ItemDTO itemDTO);
    // 품목 삭제
    boolean removeItem(String itemCode);
    // 계약등록
    void registerContract(ContractDTO contractDTO);
    // 계약 리스트 보기
    List<ContractDTO> getListContract();
    // 계약 삭제
    boolean removeContract(String cCode);
    // 조달계획등록
    void registerPlan(ProcurementPlanDTO planDTO);
    // 조달계획 리스트 보기
    List<ProcurementPlanDTO> getListPlan();
}
