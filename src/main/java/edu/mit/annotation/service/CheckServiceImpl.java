package edu.mit.annotation.service;

import edu.mit.annotation.dto.ProgressCheckDTO;
import edu.mit.annotation.dto.PurchaseOrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckServiceImpl implements CheckService{


    @Override
    public List<ProgressCheckDTO> progressCheckList() {
        return null;
    }

    @Override
    public List<PurchaseOrderDTO> PurchaseOrderList(String poCode) {
        return null;
    }
}
