package edu.mit.annotation.service;

import edu.mit.annotation.dto.ProgressCheckDTO;
import edu.mit.annotation.dto.PurchaseOrderDTO;
import edu.mit.annotation.testdto.OrderDTO;

import java.util.List;

public interface CheckService {




    // 발주서 정보 가져오기
    OrderDTO orderList();

    List<ProgressCheckDTO> progressCheckList();
    // 진척검수 정보 추가

    void getbusiNum(PurchaseOrderDTO poCode);

}