package edu.mit.annotation.service;


import edu.mit.annotation.dto.ProcurementPlanDTO;
import edu.mit.annotation.dto.PurchaseOrderDTO;
import edu.mit.annotation.mapper.OrderMapper;
import edu.mit.annotation.realdto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderMapper orderMapper;


    @Override
    public ListWithPaging<ProcPlanNoPO> getProcPlanListWithNoPO(Criteria cri) {
        return pagingSupport(orderMapper.getProcPlanList(cri), cri);
    }

    @Override
    public CompanyInfoDTO getCompInfo(String business_number) {
        return orderMapper.getCompanyInfo(business_number);
    }

    @Override
    public List<PurchOrderItemsDTO> getPOItems(String prcpNumbers) {
        return orderMapper.getPurchOrderItemList(prcpNumbers);
    }

    @Override
    public Integer savePurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO) {
        String testPoNumber = orderMapper.getLatestPurchOrderNumber();
        int number = Integer.parseInt(testPoNumber.split("-")[1])+1;
        newPurchaseOrderDTO.setPurch_order_number("po-"+number);

        while (!testValidPurchOrderNumber(newPurchaseOrderDTO)){
            number+=1;
            newPurchaseOrderDTO.setPurch_order_number("po-"+number);
        }

        String validPoNum = newPurchaseOrderDTO.getPurch_order_number();
        for (NewPurchOrderItem item :newPurchaseOrderDTO.getNewPurchOrderItem()) {
            item.setPurch_order_number(validPoNum);
            orderMapper.savePurchaseOrderItem(item);
        }

        return null;
    }

    private Boolean testValidPurchOrderNumber(NewPurchaseOrderDTO newPurchaseOrderDTO){
        try{
            orderMapper.savePurchaseOrder(newPurchaseOrderDTO);
        }catch (DuplicateKeyException e){
            return false;
        }
        return true;
    }

    private <T> ListWithPaging<T> pagingSupport(List<T> list, Criteria cri){
        Long dataCount = orderMapper.getSearchDataCount();
        if(list.isEmpty()){
            return ListWithPaging.<T>builder().msg("NO DATA").build();
        }
        Integer pageCount = (int) (dataCount % cri.getAmount() == 0 ? (dataCount/ cri.getAmount()) : (dataCount/ cri.getAmount())+1);
        List<String> pageList = new ArrayList<>();
        int nowPage = cri.getPageNum()/cri.getAmount() +1;
        if(nowPage > 10){
            pageList.add("prev");
        }
        Integer startPage = (int) ((Math.ceil(nowPage/10.0))*10 - 9);
        for(int i = startPage; i < startPage+10; i++){
            if(i <= pageCount){
                pageList.add(i+"");
            }else {
                break;
            }
        }
        Integer lastPage = Integer.parseInt(pageList.get(pageList.size()-1));
        if(lastPage < pageCount){
            pageList.add("next");
        }
        return ListWithPaging.<T>builder()
                .page_count(pageCount)
                .currentPage(nowPage)
                .pageList(pageList)
                .dataList(list)
                .build();
    }

}
