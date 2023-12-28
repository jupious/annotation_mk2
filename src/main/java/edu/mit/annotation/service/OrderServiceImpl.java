package edu.mit.annotation.service;


import edu.mit.annotation.dto.ProcurementPlanDTO;
import edu.mit.annotation.dto.PurchaseOrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Override
    public List<ProcurementPlanDTO> getProcurementPlanlist() {
        return null;
    }

    @Override
    public List<PurchaseOrderDTO> getPurchaseOrderlist() {
        return null;
    }
}
