package edu.mit.annotation.service;

import edu.mit.annotation.dto.ProcurementPlanDTO;
import edu.mit.annotation.dto.PurchaseOrderDTO;

import java.util.List;

public interface OrderService {
    //조달계획 리스트 불러오기 (조달계획번호,조달수량,품목코드,조달납기일,진행단계,평균반품률)
    List<ProcurementPlanDTO> getProcurementPlanlist();

    //조달계획 리스트에 없는 업체명,품목명 '품목코드'로 불러오기


    //발주서 리스트보기 (발주번호,발주일,발주납기일,마감여부,발주내용리스트,사업자번호)
    List<PurchaseOrderDTO> getPurchaseOrderlist();

    //발주서 리스트에 없는 업체명 '사업자번호'로 불러오기







}
