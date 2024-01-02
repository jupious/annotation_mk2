package edu.mit.annotation.controller;

import edu.mit.annotation.dto.TestContractDTO;
import edu.mit.annotation.dto.TestItemDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @PostMapping("/reg/itemInput")
    public String itemInput(@RequestBody TestItemDTO dto) {
        System.out.println("전송된 데이터 : " + dto.getUnitCode() +", " + dto.getAssyCode() +", " + dto.getPartCode() +", " + dto.getItemName() + ", " + dto.getWidth() +", " + dto.getLength() +", " + dto.getHeight() +", " + dto.getMaterial());
        return "전송완료";
    }

    @PostMapping("/reg/contractInput")
    public String contractInput(@RequestBody TestContractDTO dto) {
        System.out.println("전송된 데이터 : " + dto.getCompanyName() +", " + dto.getBusinessNumber());
        return "전송완료";
    }


}
