package edu.mit.annotation.mapper;

import edu.mit.annotation.realdto.CompanyInfoDTO;
import edu.mit.annotation.realdto.Criteria;
import edu.mit.annotation.realdto.ProcPlanNoPO;
import edu.mit.annotation.realdto.PurchOrderItemsDTO;

import java.util.List;

public interface OrderMapper {
    //검색결과 총 갯수
    Long getSearchDataCount();

    //미발주 조달계획 리스트
    List<ProcPlanNoPO> getProcPlanList(Criteria cri);

    //발주서 회사정보
    CompanyInfoDTO getCompanyInfo(String business_number);
    //발주서 품목정보
    List<PurchOrderItemsDTO> getPurchOrderItemList(String prcpNumbers);


}
