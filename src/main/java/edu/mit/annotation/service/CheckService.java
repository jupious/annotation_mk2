package edu.mit.annotation.service;

import edu.mit.annotation.dto.ProgressCheckDTO;
import edu.mit.annotation.dto.PurchaseOrderDTO;

import java.util.List;

public interface CheckService {
    List<ProgressCheckDTO> progressCheckList();
    // 진척검수 정보 추가

    List<PurchaseOrderDTO> PurchaseOrderList(String poCode);
    // 발주서 정보 가져오기


}
