package edu.mit.annotation.service;


import edu.mit.annotation.testdto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RegisterService {

    // 품목 리스트 보기
    List<TestItemDTO> getListItem();
    List<TestItemDTO> getListItemWithPaging(Criteria cri);
    List<TestItemDTO> searchListItemWithPaging(Criteria cri);
    int getTotalItemCount(Criteria cri);
    // 품목등록
    void registerItem(TestItemDTO dto);

    String getUniqueItemCode(String item_code);
    // 대분류 코드
    List<TestCodeDTO> getListUnitCode();
    void inputUnitCode(TestCodeDTO codeDTO);
    String getUniqueUnitCode(String unit_code);
    
    // 중분류 코드
    List<TestCodeDTO> getListAssyCode();
    void inputAssyCode(TestCodeDTO codeDTO);
    String getUniqueAssyCode(String assy_code);
    
    // 소분류 코드
    List<TestCodeDTO> getListPartCode();
    void inputPartCode(TestCodeDTO codeDTO);
    String getUniquePartCode(String part_code);
    boolean removeItem(String item_code);

    List<Map<String, String>> auto_business_number(String business_number);

    // 계약 리스트 보기
    List<ContractListDTO> getListContract();
    List<ContractListDTO> getListContractWithPaging(Criteria cri);
    List<ContractListDTO> searchListContractWithPaging(Criteria cri);
    int getTotalContractCount(Criteria cri);

    boolean removeContract(@Param("contract_number") String contract_number);
    void registerContract(ContractDTO dto);
    String getUniqueContractNumber(String contract_number);

    List<ProductionPlanDTO> getListprodPlan();
    List<PlanTable1> getListforTable1();
    void registerPlan(ProcurementPlanDTO dto);
    String getUniqueProcPlanNumber(String proc_plan_number);

    List<ProductionPlanDTO> getListProdPlanWithPaging(Criteria cri);
    List<ProductionPlanDTO> searchListProdPlanWithPaging(Criteria cri);
    int getTotalProdPlanCount(Criteria cri);

  /*

    // 조달계획 리스트 보기 -> 생산계획에서 가져옴
    List<ProcurementPlanDTO> getListPlan();
    // 조달계획등록
    void registerPlan(ProcurementPlanDTO planDTO);
   */
}
