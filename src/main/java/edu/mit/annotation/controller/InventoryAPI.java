package edu.mit.annotation.controller;

import edu.mit.annotation.repository.TemporalRepository;
import edu.mit.annotation.testdto.TemporalReceiveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/invapi")
@RequiredArgsConstructor
public class InventoryAPI {

    private final TemporalRepository temporalRepository;

    @PutMapping("/receive-save")
    public void receiveSave(String itemCode, Integer fairQuantity, Integer returnQuantity){
        TemporalReceiveDTO rDto = TemporalReceiveDTO.builder()
                .receiveDate(new Date())
                .fairQuantity(fairQuantity)
                .returnQuantity(returnQuantity)
                .itemCode(itemCode)
                .build();
        temporalRepository.saveReceive(rDto);
    }

    @GetMapping("/get-one-receive-data")
    public TemporalReceiveDTO getOneReceiveData(String itemCode){
       return temporalRepository.findByItemCode(itemCode);
    }

    @GetMapping("/get-all-receive-data")
    public List<TemporalReceiveDTO> getAllReceiveData(){
        return temporalRepository.findAllReceiveData();
    }
}
