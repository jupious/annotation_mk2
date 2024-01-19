package edu.mit.annotation.mapper;

import edu.mit.annotation.realdto.Criteria;
import edu.mit.annotation.realdto.ItemSaveDTO;
import edu.mit.annotation.realdto.SearchDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class InventoryMapperTests {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Test
    public void testSearchReceiveItemList() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Criteria cri = Criteria.builder()
                .pageNum(5)
                .amount(5)
                .type("IC")
                .keyword("%C1%")
                .startDate(sdf.parse("20240101"))
                .endDate(sdf.parse("20240321"))
                .build();

        inventoryMapper.searchReceiveItemList(cri);
        inventoryMapper.getSearchDataCount();
    }

    @Test
    public void testGetReceiveHistory() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SearchDTO dto = SearchDTO.builder()
                .item_code("C1G1C1_1")
                .startDate(sdf.parse("20240111"))
                .endDate(sdf.parse("20240131"))
                .build();
        inventoryMapper.getReceiveHistoryBefore(dto);
    }

    @Test
    public void testSaveItem(){
        ItemSaveDTO dto = ItemSaveDTO.builder()
                .item_code("C1G1C1_2")
                .received_quantity(32)
                .build();
        inventoryMapper.saveReceiveItem(dto);
    }

    @Test
    public void testCloseProcPlan(){
        inventoryMapper.closingProcPlan("prc-3");
    }

    @Test
    public void testFindPONumber(){
        inventoryMapper.findPurchaseOrder("prc-1");
    }

    @Test
    public void testPOClosingStat(){
        inventoryMapper.getProcPlanCloingStatus("po-1").forEach(System.out::println);
    }

    @Test
    public void testGetINCN(){
        inventoryMapper.getItemCompanyName("po-2");
    }

    @Test
    public void testGetStatementInfo(){
        inventoryMapper.getStatementInfo("po-1").forEach(System.out::println);
    }

    @Test
    public void testGetReleaseList() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Criteria cri = Criteria.builder()
                .pageNum(5)
                .amount(5)
                .type("IC")
                .keyword("%C1%")
                .startDate(sdf.parse("20240101"))
                .endDate(sdf.parse("20240321"))
                .build();
        inventoryMapper.getReleaseList(cri).forEach(System.out::println);
    }
}
