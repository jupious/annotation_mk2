package edu.mit.annotation.service;

import edu.mit.annotation.realdto.Criteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
public class InventoryServiceImplTests {
    @Autowired
    private InventoryService inventoryService;


    @Test
    public void testSearch() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Criteria cri = Criteria.builder()
                .pageNum(0)
                .amount(5)
                .type("IC")
                .keyword("%C1%")
                .startDate(sdf.parse("20240101"))
                .endDate(sdf.parse("20240321"))
                .build();
        System.out.println(inventoryService.searchReceiveItemList(cri));
        ;
    }

    @Test
    public void testGetClosedPO() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Criteria cri = Criteria.builder()
                .pageNum(0)
                .amount(5)
                .type("IN")
                .keyword("%1%")
                .startDate(sdf.parse("20240101"))
                .endDate(sdf.parse("20240321"))
                .build();
        System.out.println(inventoryService.getClosedPrc(cri));
    }



    @Test
    public void testGetReleaseData() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Criteria cri = Criteria.builder()
                .pageNum(5)
                .amount(5)
                .type("IC")
                .keyword("%C1%")
                .startDate(sdf.parse("20240101"))
                .endDate(sdf.parse("20240321"))
                .build();
        System.out.println(inventoryService.getReleaseData(cri));
    }

    @Test
    public void testGetInvCalcData() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Criteria cri = Criteria.builder()
                .pageNum(0)
                .amount(10)
                .type("IC")
                .keyword("%%")
                .order("IAD")
                .startDate(sdf.parse("20240101"))
                .endDate(sdf.parse("20240125"))
                .build();
        System.out.println(inventoryService.getInvCalcData(cri));
    }

}
