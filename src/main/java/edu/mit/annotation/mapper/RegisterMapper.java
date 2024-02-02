package edu.mit.annotation.mapper;

import edu.mit.annotation.realdto.InputCompItemDTO;
import edu.mit.annotation.testdto.*;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RegisterMapper {

    List<TestMemberDTO> findAll();

    List<ItemDTO> getListItem();
    int getTotalItemCount(RegisterCriteria cri);
    List<ItemDTO> getListItemWithPaging(RegisterCriteria cri);
    List<CodeDTO> getListUnitCode();
    void inputUnitCode(CodeDTO codeDTO);
    List<CodeDTO> getListAssyCode();
    void inputAssyCode(CodeDTO codeDTO);
    List<CodeDTO> getListPartCode();
    void inputPartCode(CodeDTO codeDTO);

    void registerItem(ItemDTO dto);
    int checkDuplicateItemCode(@Param("item_code") String item_code);
    int checkDuplicateUnitCode(@Param("unit_code") String unit_code);
    int checkDuplicateAssyCode(@Param("assy_code") String assy_code);
    int checkDuplicatePartCode(@Param("part_code") String part_code);
    int removeItem(@Param("item_code") String item_code);

    List<CoopDTO> getListCoop();
    @MapKey("business_number")
    List<Map<String, String>> auto_business_number(String business_number);

    void registerContract(ContractDTO dto);
    void registerContractItem(ContractItemDTO dto);
    void updateContract(ContractDTO dto);
    void updateContractItem(ContractDTO dto);
    List<ContractListDTO> getListContract();
    int getTotalNoContractCount();
    List<ContractListDTO> getListContractWithPaging(RegisterCriteria cri);

    int removeContract(String contract_number);

    List<ContractListDTO> checkContract(RegisterCriteria cri);

    int checkDuplicateContractNumber1(String contract_number);
    int checkDuplicateContractNumber2(String contract_number);

    List<PlanTable1> getListforTable1();
    List<ProductionPlanDTO> getListprodPlan();

    List<ProductionPlanDTO> getListProdPlanWithPaging(RegisterCriteria cri);
    List<ProductionPlanDTO> searchListProdPlanWithPaging(RegisterCriteria cri);
    int getTotalProdPlanCount(RegisterCriteria cri);

    void registerPlan(ProcurementPlanDTO dto);
    int checkDuplicateProcPlanNumber(String proc_plan_number);

    //시연용 제품구성품목등록
    void insertCompItem(InputCompItemDTO dto);

}
