package edu.mit.annotation.controller;

import edu.mit.annotation.service.RegisterService;
import edu.mit.annotation.testdto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/reg")
public class RegisterController {

    @Autowired
    RegisterService service;

    private final String blueprintuploadPath = "c:\\upload\\blueprint\\";
    private final String contractuploadPath = "c:\\upload\\contract\\";

    @GetMapping("/item")
    public void item(RegisterCriteria cri, Model model) {
        cri.setOffset((cri.getPageNum() - 1) * cri.getAmount());
        model.addAttribute("itemList", service.getListItemWithPaging(cri));
        int total = service.getTotalNoContractCount();
        model.addAttribute("pageMaker", new PageDTO(cri, total));
        model.addAttribute("unitCodeList", service.getListUnitCode());
        model.addAttribute("assyCodeList", service.getListAssyCode());
        model.addAttribute("partCodeList", service.getListPartCode());
    }

    @PostMapping("/itemInput")
    public String itemInput(@RequestParam("unit_code") String unit_code,
                            @RequestParam("assy_code") String assy_code,
                            @RequestParam("part_code") String part_code,
                            @RequestParam("item_name") String item_name,
                            @RequestParam("width") float width,
                            @RequestParam("length") float length,
                            @RequestParam("height") float height,
                            @RequestParam("material") String material,
                            @RequestParam("blueprint_origin_name") String blueprint_origin_name,
                            @RequestParam("blueprint") MultipartFile blueprint,
                            @RequestParam("unit_code_flag") boolean unit_code_flag,
                            @RequestParam("assy_code_flag") boolean assy_code_flag,
                            @RequestParam("part_code_flag") boolean part_code_flag,
                            Model model, RegisterCriteria cri) {

        ItemDTO dto = new ItemDTO();
        CodeDTO codeDTO = new CodeDTO();
        UUID uuid = UUID.randomUUID();
        String blueprint_save_name = uuid.toString() + "_" + blueprint_origin_name;

        // unit_code가 직접입력일 경우
        if(unit_code_flag==true) {
            if (Pattern.matches("^[a-zA-Z]*$", String.valueOf(unit_code.charAt(0))))    {
                codeDTO.setUnit_code(String.valueOf(Character.toUpperCase(unit_code.charAt(0))));
                String uniqueUnitCode = service.getUniqueUnitCode(codeDTO.getUnit_code());
                codeDTO.setUnit_code(uniqueUnitCode);
                codeDTO.setUnit_name(unit_code.toUpperCase());
                service.inputUnitCode(codeDTO);
                unit_code = (codeDTO.getUnit_code());
                dto.setUnit_code(unit_code);
            }
            else if(Pattern.matches("^[ㄱ-ㅎ가-힣]*$", String.valueOf(unit_code.charAt(0)))) {
                String[] chosungMapping={"G","GG","N","D","DD","R","M","B","BB","S","SS","O","J","JJ","C","K","T","P","H"};
                char uniVal=unit_code.charAt(0);
                char cho=(char)((uniVal-0xAC00)/28/21);
                String chosung=chosungMapping[(int)cho];

                codeDTO.setUnit_code(chosung);
                String uniqueUnitCode = service.getUniqueUnitCode(codeDTO.getUnit_code());
                codeDTO.setUnit_code(uniqueUnitCode);
                codeDTO.setUnit_name(unit_code);
                service.inputUnitCode(codeDTO);
                unit_code = (codeDTO.getUnit_code());
                dto.setUnit_code(unit_code);
            }
        } else {
            dto.setUnit_code(unit_code);
        }
        // assy_code가 직접입력일 경우
        if(assy_code_flag==true) {
            if (Pattern.matches("^[a-zA-Z]*$", String.valueOf(assy_code.charAt(0))))    {
                codeDTO.setAssy_code(String.valueOf(Character.toUpperCase(assy_code.charAt(0))));
                String uniqueAssyCode = service.getUniqueAssyCode(codeDTO.getAssy_code());
                codeDTO.setAssy_code(uniqueAssyCode);
                codeDTO.setAssy_name(assy_code.toUpperCase());
                service.inputAssyCode(codeDTO);
                assy_code = (codeDTO.getAssy_code());
                dto.setAssy_code(assy_code);
            }
            else if(Pattern.matches("^[ㄱ-ㅎ가-힣]*$", String.valueOf(assy_code.charAt(0)))) {
                String[] chosungMapping={"G","GG","N","D","DD","R","M","B","BB","S","SS","O","J","JJ","C","K","T","P","H"};
                char uniVal=assy_code.charAt(0);
                char cho=(char)((uniVal-0xAC00)/28/21);
                String chosung=chosungMapping[(int)cho];

                codeDTO.setAssy_code(chosung);
                String uniqueAssyCode = service.getUniqueAssyCode(codeDTO.getAssy_code());
                codeDTO.setAssy_code(uniqueAssyCode);
                codeDTO.setAssy_name(assy_code);
                service.inputAssyCode(codeDTO);
                assy_code = (codeDTO.getAssy_code());
                dto.setAssy_code(assy_code);
            }
        } else {
            dto.setAssy_code(assy_code);
        }
        // part_code가 직접입력일 경우
        if(part_code_flag==true) {
            if (Pattern.matches("^[a-zA-Z]*$", String.valueOf(part_code.charAt(0))))    {
                codeDTO.setPart_code(String.valueOf(Character.toUpperCase(part_code.charAt(0))));
                String uniquePartCode = service.getUniquePartCode(codeDTO.getPart_code());
                codeDTO.setPart_code(uniquePartCode);
                codeDTO.setPart_name(part_code.toUpperCase());
                service.inputPartCode(codeDTO);
                part_code = (codeDTO.getPart_code());
                dto.setPart_code(part_code);
            }
            else if(Pattern.matches("^[ㄱ-ㅎ가-힣]*$", String.valueOf(part_code.charAt(0)))) {
                String[] chosungMapping={"G","GG","N","D","DD","R","M","B","BB","S","SS","O","J","JJ","C","K","T","P","H"};
                char uniVal=part_code.charAt(0);
                char cho=(char)((uniVal-0xAC00)/28/21);
                String chosung=chosungMapping[(int)cho];

                codeDTO.setPart_code(chosung);
                String uniquePartCode = service.getUniquePartCode(codeDTO.getPart_code());
                codeDTO.setPart_code(uniquePartCode);
                codeDTO.setPart_name(part_code);
                service.inputPartCode(codeDTO);
                part_code = (codeDTO.getPart_code());
                dto.setPart_code(part_code);
            }
        } else {
            dto.setPart_code(part_code);
        }

        dto.setItem_code(unit_code+assy_code+part_code+"_001");
        dto.setItem_name(item_name);
        dto.setWidth(width);
        dto.setLength(length);
        dto.setHeight(height);
        dto.setMaterial(material);
        dto.setBlueprint_save_name(blueprint_save_name);
        dto.setBlueprint_origin_name(blueprint_origin_name);
        service.registerItem(dto);

        File uploadDir = new File(blueprintuploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String filePath = blueprintuploadPath + File.separator + blueprint_save_name;
        try {
            blueprint.transferTo(new File(filePath));
            System.out.println("파일 업로드 성공: " + filePath);
        } catch (IOException e) {
            System.out.println("파일 업로드 실패: " + e.getMessage());
        }
        System.out.println("품목코드 : " + dto.getItem_code() + " 품목명 : " + item_name +  " 규격 : " + width + " x " + length+  " x " + height+ " 재질 : " + material +  " 도면 : " + blueprint_origin_name);

        List<ItemDTO> updateList = service.getListItemWithPaging(cri);
        model.addAttribute("updateList", updateList);
        System.out.println(updateList);
        return "redirect:/reg/item";
    }

    @PostMapping("/removeItem")
    public String removeItem(@RequestParam("item_code") String item_code)  {
        service.removeItem(item_code);
        return "redirect:/reg/item";
    }

    @GetMapping("/contract")
    public void contract(RegisterCriteria cri, Model model)   {
        cri.setOffset((cri.getPageNum()-1)*cri.getAmount());

        if(cri.getFlag()==1)   {
            model.addAttribute("contractList", service.getListContractWithPaging(cri));
            model.addAttribute("pageMaker", new PageDTO(cri, service.getTotalNoContractCount()));
        }
        else {
            model.addAttribute("contractList", service.checkContract(cri));
            model.addAttribute("pageMaker", new PageDTO(cri, service.getTotalNoContractCount()));
        }

    }

    @GetMapping("/updateContractList")
    public String updateContractList(Model model) {
        model.addAttribute("updatedContractList", service.getListContract());

        return "redirect:/reg/contract";
    }

    @PostMapping("/contractInput")
    public String contractInput(@RequestParam("contract_origin_name") String contract_origin_name,
                                @RequestParam("business_number") String business_number,
                                @RequestParam("note") String note,
                                @RequestParam("item_code[]") List<String> item_code,
                                @RequestParam("item_name[]") List<String> item_name,
                                @RequestParam("item_price[]") List<Integer>item_price,
                                @RequestParam("lead_time[]") List<Integer> lead_time,
                                @RequestParam("details[]") List<String> details,
                                @RequestParam("contract") MultipartFile contract,
                                @ModelAttribute formDTO formData)    {

        UUID uuid = UUID.randomUUID();
        ContractDTO contractDTO = new ContractDTO();
        ContractItemDTO contractItemDTO = new ContractItemDTO();

        String contract_save_name=uuid.toString()+"-"+contract_origin_name;
        System.out.println(formData);
        contractDTO.setContract_number("co-1");
        contractDTO.setContract_origin_name(contract_origin_name);
        contractDTO.setBusiness_number(business_number);
        contractDTO.setNote(note);
        contractDTO.setContract_save_name(contract_save_name);
        service.registerContract(contractDTO);

        for (int i = 0; i < item_code.size(); i++) {

            contractItemDTO.setItem_code(item_code.get(i));
            contractItemDTO.setItem_name(item_name.get(i));
            contractItemDTO.setItem_price(item_price.get(i));
            contractItemDTO.setLead_time(lead_time.get(i));
            contractItemDTO.setDetails(details.get(i));
            contractItemDTO.setContract_number(contractDTO.getContract_number());
            service.registerContractItem(contractItemDTO);
        }

            File uploadDir = new File(contractuploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String filePath = contractuploadPath + File.separator + contract_save_name;
            try {
                contract.transferTo(new File(filePath));
                System.out.println("파일 업로드 성공: " + filePath);
            } catch (IOException e) {
                System.out.println("파일 업로드 실패: " + e.getMessage());
            }
        return "redirect:/reg/contract";
    }

    @PostMapping("/removeContract")
    public String removeContract(@RequestParam("contract_number") String contract_number) {
        service.removeContract(contract_number);
        System.out.println("삭제된 계약번호 : "+service.removeContract(contract_number));
        return "redirect:/reg/contract";
    }

    @GetMapping("/plan")
    public void plan(RegisterCriteria cri, Model model)   {
        cri.setOffset((cri.getPageNum()-1)*cri.getAmount());

        LocalDate startDate = cri.getParsedStartDate();
        LocalDate endDate = cri.getParsedEndDate();


        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("prodPlan", service.getListProdPlanWithPaging(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, service.getTotalNoContractCount()));
    }

    @PostMapping("/planInput")
    public String planInput(@RequestParam("prod_start_date[]") List<String> prod_start_date,
                            @RequestParam("prod_end_date[]") List<String> prod_end_date,
                            @RequestParam("product_code[]") List<String> product_code,
                            @RequestParam("product_name[]") List<String> product_name,
                            @RequestParam("prod_quantity[]") List<Integer> prod_quantity,
                            @RequestParam("item_code[]") List<String> item_code,
                            @RequestParam("item_name[]") List<String> item_name,
                            @RequestParam("procure_term[]") List<Integer> procure_term,
                            @RequestParam("avg_return_rate[]") List<Float> avg_return_rate,
                            @RequestParam("proc_quantity[]") List<Integer> proc_quantity,
                            @RequestParam("proc_duedate[]") List<String> proc_duedate)   {

        System.out.println("prod_start_date : " + prod_start_date);
        System.out.println("prod_end_date : " + prod_end_date);
        System.out.println("product_code : " + product_code);
        System.out.println("product_name : " + product_name);
        System.out.println("prod_quantity : " + prod_quantity);
        System.out.println("item_code : " + item_code);
        System.out.println("item_name : " + item_name);
        System.out.println("procure_term : " + procure_term);
        System.out.println("avg_return_rate : " + avg_return_rate);
        System.out.println("proc_quantity : " + proc_quantity);
        System.out.println("proc_duedate : " + proc_duedate);

        for (int i = 0; i < item_code.size(); i++) {
            ProcurementPlanDTO dto = new ProcurementPlanDTO();

            dto.setProc_plan_number("prc-1");
            dto.setItem_code(item_code.get(i));
            dto.setProc_quantity(proc_quantity.get(i));
            dto.setProc_duedate(Date.valueOf(proc_duedate.get(i)));
            dto.setProc_progress(false);

            service.registerPlan(dto);
        }

        return "redirect:/reg/plan";
    }

}
