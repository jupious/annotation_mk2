package edu.mit.annotation.service;

import edu.mit.annotation.mapper.InventoryMapper;
import edu.mit.annotation.realdto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryMapper inventoryMapper;


    @Override
    public ListWithPaging<ReceiveItemDTO> searchReceiveItemList(Criteria cri) {
        List<ReceiveItemDTO> list = inventoryMapper.searchReceiveItemList(cri);

        return pagingSupport(list,cri);
    }

    @Override
    public Long getReceiveHistory(SearchDTO dto) {
        Long sum = 0L;
        for (Long x : inventoryMapper.getReceiveHistoryBefore(dto)) {
            sum += x;
        }
        return sum;
    }

    @Override
    public Integer saveReceivedItem(ItemSaveDTO dto) {
        return inventoryMapper.saveReceiveItem(dto);
    }

    @Override
    public Integer closingProcPlan(String proc_plan_number) {

        return inventoryMapper.closingProcPlan(proc_plan_number);
    }

    @Override
    public void closingPO(String proc_plan_number) {
        String poNum = inventoryMapper.findPurchaseOrder(proc_plan_number);
        boolean isOne = true;
        for (Integer x : inventoryMapper.getProcPlanCloingStatus(poNum)) {
            if (x != 1)
                isOne = false;
        }
        if(isOne){
            inventoryMapper.closePurchaseOrder(poNum);
            System.out.println(poNum+"발주서가 마감되었습니다.");
        }
    }

    @Override
    public ListWithPaging<ClosedPurchaseOrderDTO> getClosedPO(Criteria cri) {
        String type = cri.getType();
        List<ClosedPurchaseOrderDTO> list = new ArrayList<>();
        if(type.equals("OC")){
            list = inventoryMapper.searchWithPONumber(cri);
        }else if(type.equals("IN")){
            list = inventoryMapper.searchWithItemName(cri);
        }else if(type.equals("CN")){
            list = inventoryMapper.searchWithCompanyName(cri);
        }

        return pagingSupport(search(list),cri);
    }

    @Override
    public StatementPreviewDTO getStatement(String purch_order_number) {
        List<StatementItemsDTO> itemsList = new ArrayList<>();
        List<StatementPrintDTO> list = inventoryMapper.getStatementInfo(purch_order_number);
        list.forEach(x -> {
            long received_quantity = getReceiveHistory(SearchDTO.builder()
                    .item_code(x.getItem_code())
                    .startDate(x.getPurch_order_date())
                    .endDate(x.getProc_duedate()).build());

            itemsList.add(StatementItemsDTO.builder()
                            .item_code(x.getItem_code())
                            .item_name(x.getItem_name())
                            .item_price(x.getItem_price())
                            .note(x.getNote())
                            .received_quantity(received_quantity)
                            .build());
        });
        StatementPrintDTO dto = list.get(0);

        return StatementPreviewDTO.builder()
                .stmtDate(new Date())
                .purch_order_detail(dto.getPurch_order_detail())
                .business_number(dto.getBusiness_number())
                .company_name(dto.getCompany_name())
                .company_address(dto.getCompany_address())
                .manager(dto.getManager())
                .manager_tel(dto.getManager_tel())
                .itemList(itemsList).build();
    }

    @Override
    public void stmtPbCntUp(String purch_order_number) {
        inventoryMapper.statementPbCntUp(purch_order_number);
    }

    @Override
    public ListWithPaging<ReleasingDTO> getReleaseData(Criteria cri) {
        List<ReleasingDTO> list = inventoryMapper.getReleaseList(cri);

        return pagingSupport(list,cri);
    }

    @Override
    public Integer saveReleaseItem(ReleaseItemDTO dto) {
        return inventoryMapper.saveReleaseItem(dto);
    }

    @Override
    public ListWithPaging<InventoryCalcDTO> getInvCalcData(Criteria cri) {
        List<InventoryCalcDTO> list = inventoryMapper.getInvCalcData(cri);

        return pagingSupport(list,cri);
    }

    @Override
    public List<InventoryReportDTO> getInvReport(Criteria cri) {
        return inventoryMapper.getInvReport(cri);
    }

    private List<ClosedPurchaseOrderDTO> search(List<ClosedPurchaseOrderDTO> list){

        for (ClosedPurchaseOrderDTO x : list) {
            List<PurchOrderItemWithCompanyName> incnList = inventoryMapper.getItemCompanyName(x.getPurch_order_number());
            x.setCompany_name(incnList.get(0).getCompany_name());
            int i = 0;
            String itemNameString = "";
            for (PurchOrderItemWithCompanyName y : incnList) {
                if(i < 3){
                    itemNameString += y.getItem_name() +", ";
                }else {
                    itemNameString = itemNameString.substring(0, itemNameString.length()-2);
                    itemNameString += "...";
                    break;
                }
                i++;
            }
            if(itemNameString.charAt(itemNameString.length()-1) == ' '){
                itemNameString = itemNameString.substring(0, itemNameString.length()-2);
            }
            x.setItem_name_string(itemNameString);
        }
        return list;
    }

    private <T> ListWithPaging<T> pagingSupport(List<T> list, Criteria cri){
        Long dataCount = inventoryMapper.getSearchDataCount();
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
