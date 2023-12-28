package edu.mit.annotation.service;

import edu.mit.annotation.dto.ContractDTO;
import edu.mit.annotation.dto.ItemDTO;
import edu.mit.annotation.dto.ProcurementPlanDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Log4j2
@Service
public abstract class RegisterServiceImpl implements  RegisterService{

    @Override
    public String registerItem(ItemDTO itemDTO) {
        return null;
    }

    @Override
    public List<ItemDTO> getListItem()  {
        List<ItemDTO> list = new ArrayList<>();
        list.add(new ItemDTO("품목코드1", "품목명1", "대분류1", "중분류1", "소분류1", 10F, 10F, 10F, "재질1", "품목명1_도면", 10));
        return list;
    }

    public void registerItem()  {

    }

    public boolean modifyItem(ItemDTO itemDTO)  {
        return true;
    }

    public boolean removeItem(String itemCode)  {
        return true;
    }

    public void registerContract(ContractDTO contractDTO)   {

    }

    public List<ContractDTO> getListContract()  {
        return new ArrayList<>();
    }

    public boolean removeContract(String cCode) {
        return true;
    }

    public void registerPlan(ProcurementPlanDTO planDTO)    {

    }

    public List<ProcurementPlanDTO> getListPlan()   {
        return new ArrayList<>();
    }



}
