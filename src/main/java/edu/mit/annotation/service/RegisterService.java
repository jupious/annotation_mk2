package edu.mit.annotation.service;


import edu.mit.annotation.testdto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RegisterService {

    // 품목 리스트 보기
    List<ItemDTO> getListItem();
    List<ItemDTO> getListItemWithPaging(RegisterCriteria cri);
    int getTotalItemCount(RegisterCriteria cri);
    // 품목등록
    void registerItem(ItemDTO dto);

    String getUniqueItemCode(String item_code);
    // 대분류 코드
    List<CodeDTO> getListUnitCode();
    void inputUnitCode(CodeDTO codeDTO);
    String getUniqueUnitCode(String unit_code);
    
    // 중분류 코드
    List<CodeDTO> getListAssyCode();
    void inputAssyCode(CodeDTO codeDTO);
    String getUniqueAssyCode(String assy_code);
    
    // 소분류 코드
    List<CodeDTO> getListPartCode();
    void inputPartCode(CodeDTO codeDTO);
    String getUniquePartCode(String part_code);
    boolean removeItem(String item_code);

    List<Map<String, String>> auto_business_number(String business_number);

    // 계약 리스트 보기
    List<ContractListDTO> getListContract();
    List<ContractListDTO> getListContractWithPaging(RegisterCriteria cri);
    List<ContractListDTO> checkContract(RegisterCriteria cri);
    int getTotalNoContractCount();

    boolean removeContract(@Param("contract_number") String contract_number);
    void registerContract(ContractDTO dto);
    void registerContractItem(ContractItemDTO dto);
    String getUniqueContractNumber1(String contract_number);
    String getUniqueContractNumber2(String contract_number);



    List<ProductionPlanDTO> getListprodPlan();
    List<PlanTable1> getListforTable1();
    void registerPlan(ProcurementPlanDTO dto);
    String getUniqueProcPlanNumber(String proc_plan_number);

    List<ProductionPlanDTO> getListProdPlanWithPaging(RegisterCriteria cri);
    int getTotalProdPlanCount(RegisterCriteria cri);

  /*

    // 조달계획 리스트 보기 -> 생산계획에서 가져옴
    List<ProcurementPlanDTO> getListPlan();
    // 조달계획등록
    void registerPlan(ProcurementPlanDTO planDTO);
   */
}
