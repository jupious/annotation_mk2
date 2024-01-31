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
    public Long getReceiveHistory(ReceiveHistorySearchDTO dto) {
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
        return inventoryMapper.saveClosedProcPlan(proc_plan_number) + inventoryMapper.closingProcPlan(proc_plan_number);
    }

    @Override
    public List<ReceiveItemDTO> autoReceiveSearch(Criteria cri) {
        return inventoryMapper.autoReceiveSearch(cri);
    }

    @Override
    public ListWithPaging<ClosedProcPlanDTO> getClosedPrc(Criteria cri) {
        return pagingSupport(inventoryMapper.getClosedProcPlan(cri),cri);
    }

    @Override
    public List<ClosedProcPlanDTO> autoPrcpSearch(Criteria cri) {
        return inventoryMapper.autoSearchClosedProcPlan(cri);
    }

    @Override
    public StatementPreviewDTO getStatement(String proc_plan_number, String business_number) {

        return StatementPreviewDTO.builder()
                .companyInfo(inventoryMapper.getCompanyInfo(business_number))
                .itemList(inventoryMapper.getStatementItems(proc_plan_number))
                .build();
    }


    @Override
    public ListWithPaging<ReleasingDTO> getReleaseData(Criteria cri) {
        List<ReleasingDTO> list = inventoryMapper.getReleaseList(cri);

        return pagingSupport(list,cri);
    }

    @Override
    public List<ReleasingDTO> autoSearchRelease(Criteria cri) {
        return inventoryMapper.autoSearchRelease(cri);
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
    public List<InventoryCalcDTO> autoSearchInvCalc(Criteria cri) {
        return inventoryMapper.autoSearchInvCalc(cri);
    }

    @Override
    public List<InventoryReportDTO> getInvReport(Criteria cri) {
        return inventoryMapper.getInvReport(cri);
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
