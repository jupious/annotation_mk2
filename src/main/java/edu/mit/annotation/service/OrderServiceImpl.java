package edu.mit.annotation.service;


import edu.mit.annotation.mapper.OrderMapper;
import edu.mit.annotation.realdto.*;
import edu.mit.annotation.testdto.ProgressCheckDTO;
import edu.mit.annotation.testdto.PurchaseOrderListDTO;
import edu.mit.annotation.testdto.RegisterCriteria;
import edu.mit.annotation.testdto.getCompanyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ProcPlanNoPO> autoSearchNoPOPrcp(Criteria cri) {
        return orderMapper.autoSearchPrcp(cri);
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

    @Override
    public ListWithPaging<PublishedPurchaseOrderDTO> getPublishedPOList(Criteria cri) {
        List<PublishedPurchaseOrderDTO> list = orderMapper.getPoList(cri);
        for(int i = 0; i < list.size(); i++){
            PublishedPurchaseOrderDTO dto = list.get(i);
            String[] sliced = dto.getItem_names().split("!@#");
            StringBuilder newItemName = new StringBuilder(sliced[0]);
            for (int j = 1; j < sliced.length; j++) {
                if (j == 3) {
                    newItemName.append("...");
                    break;
                } else {
                    newItemName.append(", ").append(sliced[j]);
                    System.out.println(newItemName);
                }
            }
            dto.setItem_names(String.valueOf(newItemName));
            list.set(i,dto);
        }

        return pagingSupport(list,cri);
    }

    @Override
    public List<PublishedPurchaseOrderDTO> autoSearchPoList(Criteria cri) {
        return orderMapper.autoSearchPOList(cri);
    }

    @Override
    public Integer isPrcpClosed(String purch_order_number) {
        return orderMapper.isPrcpClosed(purch_order_number);
    }

    @Override
    public void deletePurchaseOrder(String purch_order_number) {
        orderMapper.deletePurchaseOrderItem(purch_order_number);
        orderMapper.deletePurchaseOrder(purch_order_number);
    }

    @Override
    public PublishedPODTO getPOinfo(String purch_order_number) {
        return orderMapper.getPOInfo(purch_order_number);
    }

    @Override
    public List<PublishedPOItemDTO> getPublishedPOItems(String purch_order_number) {
        return orderMapper.getPOitems(purch_order_number);
    }

    @Override
    public List<CompanyInfoDTO> getCompWithEmail(String email) {
        return orderMapper.getCompInfoWithEmail(email);
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 아래부터 나중에 이어붙일 부분
    @Override
    public List<PurchaseOrderListDTO> getListforTable1(RegisterCriteria cri)    {

        return orderMapper.getListforTable1(cri);
    }

    @Override
    public List<ProgressCheckDTO> getListforProgressCheck(String proc_plan_number, String purch_order_number) {
        return orderMapper.getListforProgressCheck(proc_plan_number, purch_order_number);
    }

    @Override
    public int checkPlanDB(String proc_plan_number) {
        return orderMapper.checkPlanDB(proc_plan_number);
    }

    @Override
    public List<AutoProgPlanAlertDTO> autoMailPrcp(Criteria cri) {
        return orderMapper.getAutoProcPlan(cri);
    }


    @Override
    public int inspectCheckPlans(String purch_order_number)    {
        return orderMapper.inspectCheckPlans(purch_order_number);
    }

    @Override
    public void saveProgressCheck(ProgressCheckDTO dto) {
        orderMapper.saveProgressCheck(dto);
        System.out.println("발주번호 : " + dto.getPurch_order_number());
        System.out.println("조달계획번호 : " + dto.getProc_plan_number());
        System.out.println("차수 : " + dto.getProc_check_order());
        System.out.println("검수일정 : " + dto.getProc_check_date());
    }

    @Override
    public void updateProgressCheck(ProgressCheckDTO dto)   {
        orderMapper.updateProgressCheck(dto);
        System.out.println("변경된 발주번호 : " + dto.getPurch_order_number());
        System.out.println("변경된 조달계획번호: " + dto.getProc_plan_number());
        System.out.println("변경된 진척검수차수: " + dto.getProc_check_order());
        System.out.println("변경된 진척검수결과: " + dto.getProg_check_result());
        System.out.println("변경된 진척검수수량: " + dto.getCompleted_quantity());
        System.out.println("변경된 보완사항 : " + dto.getSupplementation());
        System.out.println("변경된 진척검수일자 : " + dto.getProc_check_date());

    }

    @Override
    public List<getCompanyDTO> getCompany(String proc_plan_number) {
        return orderMapper.getCompany(proc_plan_number);
    }


    @Override
    public List<PurchaseOrderListDTO> getListPurOrder()    {
        return orderMapper.getListPurOrder();
    }

    @Override
    public int getTotalPurOrder(RegisterCriteria cri)  {
        return orderMapper.getTotalPurOrder(cri);
    }

    @Override
    public int getCountListPurOrder()   {
        return orderMapper.getCountListPurOrder();
    }

    @Override
    public int getCountProcPlan(RegisterCriteria cri)  {
        return orderMapper.getCountProcPlan(cri);
    }

    @Override
    public int getCountPublishedPurOrder(RegisterCriteria cri) {
        return orderMapper.getCountPublishedPurOrder(cri);
    }

    @Override
    public int getCountProgCheckingProcPlan(RegisterCriteria cri)  {
        return orderMapper.getCountProgCheckingProcPlan(cri);
    }

    @Override
    public int getCountFinishedProcPlan(RegisterCriteria cri)  {
        return orderMapper.getCountFinishedProcPlan(cri);
    }

}
