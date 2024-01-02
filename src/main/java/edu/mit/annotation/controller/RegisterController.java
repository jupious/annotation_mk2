package edu.mit.annotation.controller;

import edu.mit.annotation.dto.TestContractDTO;
import edu.mit.annotation.dto.TestItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public void contract()   {
    }

    @GetMapping("/plan")
    public void plan()   {
    }


}
