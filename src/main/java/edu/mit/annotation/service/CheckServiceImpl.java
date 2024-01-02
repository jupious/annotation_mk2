package edu.mit.annotation.service;

import edu.mit.annotation.dto.ProgressCheckDTO;
import edu.mit.annotation.dto.PurchaseOrderDTO;
import edu.mit.annotation.testdto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CheckServiceImpl implements CheckService{


    @Override // 발주서 정보 가져오기
    public OrderDTO orderList() {
        OrderDTO order1 = new OrderDTO();
        order1.setPoCode("P-00001");
        order1.setComName("그린피엔엘");
        order1.setPName("키보드");
        order1.setResultnum("2차완료");
        order1.setProgress(80);
        order1.setOrderDate("2023-11-20");
        order1.setDeldate("2024-01-02");
        order1.setBusinessNum("192-168-35-123");
        order1.setEName("이준영");



        return order1;
    }
    @Override // 진척 검수 정보  추가
    public List<ProgressCheckDTO> progressCheckList() {
//        ProgressCheckDTO progressCheckDTOS = new ArrayList<>();
//
//        ProgressCheckDTO check1 =

        return null;
    }

    @Override // 사업자등록번호를 이용해서 업체명을 가져오는
    public void getbusiNum(PurchaseOrderDTO poCode) {

    }
}




