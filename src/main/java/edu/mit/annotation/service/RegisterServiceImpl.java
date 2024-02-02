package edu.mit.annotation.service;

import edu.mit.annotation.mapper.RegisterMapper;
import edu.mit.annotation.realdto.InputCompItemDTO;
import edu.mit.annotation.testdto.*;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Random;

@AllArgsConstructor
@Primary
@Service
public class RegisterServiceImpl implements RegisterService {

    RegisterMapper mapper;

    @Override
    public List<ItemDTO> getListItem()  {
        return mapper.getListItem();
    }

    @Override
    public List<ItemDTO> getListItemWithPaging(RegisterCriteria cri)    {
        return mapper.getListItemWithPaging(cri);
    }


    @Override
    public int getTotalItemCount(RegisterCriteria cri)  {
        return mapper.getTotalItemCount(cri);
    }

    @Override
    public void registerItem(ItemDTO dto) {
        Random rd = new Random();
        try{
            mapper.registerItem(dto);
            InputCompItemDTO input = InputCompItemDTO.builder()
                    .item_code(dto.getItem_code())
                    .product_code("PROD002")
                    .item_required_quantity(rd.nextInt(40)+1)
                    .avg_return_rate(rd.nextDouble(4)+0.3).build();
            System.out.println("생산정보등록됨?");
            mapper.insertCompItem(input);
        }
        catch (DuplicateKeyException e) {
            e.printStackTrace();
            dto.setItem_code((getUniqueItemCode(dto.getItem_code())));
            registerItem(dto);
        }
        System.out.println("결과 : " + dto.getItem_code());
    }
    @Override
    public String getUniqueItemCode(String item_code) {

        int index = Integer.parseInt(item_code.split("_")[1]);
        String baseItemCode = item_code.substring(0, 7);
        System.out.println("index : " + index);
        System.out.println("baseItemCode : " + baseItemCode);

            while (mapper.checkDuplicateItemCode(item_code) > 0) {
                index++;
                item_code = baseItemCode + String.format("%03d", index);
            }
        return item_code;
    }

    @Override
    public List<CodeDTO> getListUnitCode() {
        return mapper.getListUnitCode();
    }

    @Override
    public void inputUnitCode(CodeDTO codeDTO) {
        mapper.inputUnitCode(codeDTO);
    }

    @Override
    public String getUniqueUnitCode(String unit_code) {
        int index = 1;

        while (mapper.checkDuplicateUnitCode(unit_code + index) > 0) {
            index++;
        }

        return unit_code + index;
    }

    @Override
    public List<CodeDTO> getListAssyCode() {
        return mapper.getListAssyCode();
    }

    @Override
    public void inputAssyCode(CodeDTO codeDTO) {
        mapper.inputAssyCode(codeDTO);
    }

    @Override
    public String getUniqueAssyCode(String assy_code) {
        int index = 1;
        while (mapper.checkDuplicateAssyCode(assy_code + index) > 0) {
            index++;
        }
        return assy_code + index;
    }

    @Override
    public List<CodeDTO> getListPartCode() {
        return mapper.getListPartCode();
    }

    @Override
    public void inputPartCode(CodeDTO codeDTO) {
        mapper.inputPartCode(codeDTO);
    }

    @Override
    public String getUniquePartCode(String part_code) {
        int index = 1;

        while (mapper.checkDuplicatePartCode(part_code + index) > 0) {
            index++;
        }

        return part_code + index;
    }

    @Transactional
    @Override
    public boolean removeItem(@RequestParam("item_code") String item_code) {
        int result = mapper.removeItem(item_code);
        if (result == 1) {
            System.out.println("삭제된 아이템코드 : " + item_code);
            return true;
        } else {
            // 삭제 실패
            System.out.println("삭제하지 못한 아이템코드 : " + item_code);
            return false;
        }
    }

    @Override
    public List<Map<String, String>> auto_business_number(String business_number) {
        return mapper.auto_business_number("%" + business_number + "%");
    }

    @Override
    public List<ContractListDTO> getListContract() {
        return mapper.getListContract();
    }

    @Override
    public List<ContractListDTO> getListContractWithPaging(RegisterCriteria cri)    {
        return mapper.getListContractWithPaging(cri);
    }

    @Override
    public List<ContractListDTO> checkContract(RegisterCriteria cri)    {
        return mapper.checkContract(cri);
    }

    @Override
    public int getTotalNoContractCount()    {
        return mapper.getTotalNoContractCount();
    }

    @Transactional
    @Override
    public boolean removeContract(@Param("contract_number") String contract_number) {
        return mapper.removeContract(contract_number) == 1 ? true : false;
    }

    @Override
    public void registerContract(ContractDTO dto) {
        try{
            mapper.registerContract(dto);
        }
        catch (DuplicateKeyException e) {
            e.printStackTrace();
            dto.setContract_number(getUniqueContractNumber1(dto.getContract_number()));
            registerContract(dto);
        }
        System.out.println("결과 : " + dto.getContract_number());
    }

    @Override
    public void registerContractItem(ContractItemDTO dto) {
        try{
            mapper.registerContractItem(dto);
        }
        catch (DuplicateKeyException e) {
            e.printStackTrace();
            dto.setContract_number(getUniqueContractNumber2(dto.getContract_number()));
            registerContractItem(dto);
        }
        System.out.println("결과 : " + dto.getContract_number());
        System.out.println("결과 : " + dto.getItem_code());
        System.out.println("결과 : " + dto.getItem_name());
    }

    @Override
    public String getUniqueContractNumber1(String contract_number) {
        int index = Integer.parseInt(contract_number.split("-")[1]);

        String baseContractNumber = "co-";
        String contractNumber = baseContractNumber + (index+1);

        while (mapper.checkDuplicateContractNumber1(contractNumber) > 0) {
            index++;
            contractNumber = baseContractNumber + index;
        }
        return contractNumber;
    }
    @Override
    public String getUniqueContractNumber2(String contract_number) {
        int index = Integer.parseInt(contract_number.split("-")[1]);

        String baseContractNumber = "co-";
        String contractNumber = baseContractNumber + (index+1);

        while (mapper.checkDuplicateContractNumber2(contractNumber) > 0) {
            index++;
            contractNumber = baseContractNumber + index;
        }
        return contractNumber;
    }

    @Override
    public List<ProductionPlanDTO> getListprodPlan() {
        return mapper.getListprodPlan();
    }

    @Override
    public List<PlanTable1> getListforTable1()  {
        return mapper.getListforTable1();
    }

    @Override
    public List<ProductionPlanDTO> getListProdPlanWithPaging(RegisterCriteria cri){
        return mapper.getListProdPlanWithPaging(cri);
    }

    @Override
    public int getTotalProdPlanCount(RegisterCriteria cri){
        return mapper.getTotalProdPlanCount(cri);
    }

    @Override
    public void registerPlan(ProcurementPlanDTO dto)    {
        try{
            mapper.registerPlan(dto);
        }
        catch(DuplicateKeyException e)  {
            e.printStackTrace();
            dto.setProc_plan_number((getUniqueProcPlanNumber(dto.getProc_plan_number())));
            registerPlan(dto);
        }
        System.out.println("결과 : " + dto.getProc_plan_number());
    }

    @Override
    public String getUniqueProcPlanNumber(String proc_plan_number)  {
        int index = Integer.parseInt(proc_plan_number.split("-")[1]);
        String baseProcPlanNumber="prc-";
        String ProcPlanNumber=baseProcPlanNumber+(index+1);

        while (mapper.checkDuplicateProcPlanNumber(ProcPlanNumber) > 0) {
            index++;
            ProcPlanNumber = baseProcPlanNumber+index;
        }

        return ProcPlanNumber;
    }

}

/*

    @Override
    public boolean removeContract(String cCode) {
        return false;
    }

    @Override
    public List<ProcurementPlanDTO> getListPlan() {
        return null;
    }

    @Override
    public void registerPlan(ProcurementPlanDTO planDTO) {

    }

     */
