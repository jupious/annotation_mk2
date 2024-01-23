package edu.mit.annotation.mapper;

import edu.mit.annotation.testdto.*;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TestMapper {

    List<TestMemberDTO> findAll();

    List<TestItemDTO> getListItem();
    int getTotalItemCount(Criteria cri);
    List<TestItemDTO> getListItemWithPaging(Criteria cri);
    List<TestItemDTO> searchListItemWithPaging(Criteria cri);
    List<TestCodeDTO> getListUnitCode();
    void inputUnitCode(TestCodeDTO codeDTO);
    List<TestCodeDTO> getListAssyCode();
    void inputAssyCode(TestCodeDTO codeDTO);
    List<TestCodeDTO> getListPartCode();
    void inputPartCode(TestCodeDTO codeDTO);

    void registerItem(TestItemDTO dto);
    int checkDuplicateItemCode(@Param("item_code") String item_code);
    int checkDuplicateUnitCode(@Param("unit_code") String unit_code);
    int checkDuplicateAssyCode(@Param("assy_code") String assy_code);
    int checkDuplicatePartCode(@Param("part_code") String part_code);
    int removeItem(@Param("item_code") String item_code);

    List<TestCoopDTO> getListCoop();
    @MapKey("business_number")
    List<Map<String, String>> auto_business_number(String business_number);

    void registerContract(ContractDTO dto);
    void registerContractItem(ContractDTO dto);
    void updateContract(ContractDTO dto);
    void updateContractItem(ContractDTO dto);
    List<ContractListDTO> getListContract();
    int getTotalContractCount(Criteria cri);
    List<ContractListDTO> getListContractWithPaging(Criteria cri);

    List<ContractListDTO> searchListContractWithPaging(Criteria cri);
    int removeContract(String contract_number);


    int checkDuplicateContractNumber(String contract_number);

    List<PlanTable1> getListforTable1();
    List<ProductionPlanDTO> getListprodPlan();

    List<ProductionPlanDTO> getListProdPlanWithPaging(Criteria cri);
    List<ProductionPlanDTO> searchListProdPlanWithPaging(Criteria cri);
    int getTotalProdPlanCount(Criteria cri);

    void registerPlan(ProcurementPlanDTO dto);
    int checkDuplicateProcPlanNumber(String proc_plan_number);

}
