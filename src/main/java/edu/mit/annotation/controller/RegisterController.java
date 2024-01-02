package edu.mit.annotation.controller;

import edu.mit.annotation.dto.TestContractDTO;
import edu.mit.annotation.dto.TestItemDTO;
import edu.mit.annotation.testdto.ContractItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/reg")
public class RegisterController {

    @GetMapping("/item")
    public void item()   {
    }

    @PostMapping("/reg/itemInput")
    public String itemInput(TestItemDTO dto, Model model)    {
        model.addAttribute("item", dto);
        return "redirect:/table2";
    }

    @PostMapping("/reg/contractInput")
    public String contractInput(TestContractDTO dto, Model model)    {
        model.addAttribute("contract", dto);
        return "redirect:/table2";
    }

    @GetMapping("/contract")
    public void contract(Model model)   {
        List<ContractItemDTO> ciDtoList = new ArrayList<>();

        for(int i = 1; i <= 6; i++){
            ContractItemDTO ciDto = ContractItemDTO.builder()
                    .cCode("c-"+i)
                    .itemCode((char)(i+64)+i+"")
                    .itemName("품목명"+i)
                    .compName("-")
                    .compNum("-")
                    .price(-1)
                    .leadTime(-1)
                    .contDone(false)
                    .build();
            if(i > 4){
                ciDto.setCompName("(주)업체명"+i);
                ciDto.setCompNum(i*111+"-"+i*11+"-"+i*11111);
                ciDto.setPrice(i*671);
                ciDto.setLeadTime(new Random().nextInt(7)+2);
                ciDto.setContDone(true);

            }
            ciDtoList.add(ciDto);
        }
        model.addAttribute("cList",ciDtoList);
    }

    @GetMapping("/plan")
    public void plan()   {
    }


}
