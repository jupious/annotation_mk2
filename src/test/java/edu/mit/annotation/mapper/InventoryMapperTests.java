package edu.mit.annotation.mapper;

import edu.mit.annotation.realdto.Criteria;
import edu.mit.annotation.realdto.ItemSaveDTO;
import edu.mit.annotation.realdto.ReceiveHistorySearchDTO;
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
        ReceiveHistorySearchDTO dto = ReceiveHistorySearchDTO.builder()
                .proc_plan_number("prc-1")
                .startDate(sdf.parse("20240111"))
                .endDate(sdf.parse("20240131"))
                .build();
        inventoryMapper.getReceiveHistoryBefore(dto);
    }

    @Test
    public void testGetClosedProcPlan() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Criteria cri = Criteria.builder()
                .pageNum(0)
                .amount(10)
                .type("CN")
                .keyword("%젬블로%")
                .startDate(sdf.parse("20240124"))
                .endDate(sdf.parse("20240224"))
                .build();
        inventoryMapper.getClosedProcPlan(cri).forEach(System.out::println);
    }


    @Test
    public void testGetStatementInfo(){
        inventoryMapper.getStatementItems("'prc-5','prc-6'").forEach(System.out::println);
    }

    @Test
    public void testGetReleaseList() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Criteria cri = Criteria.builder()
                .pageNum(0)
                .amount(10)
                .type("IC")
                .keyword("%G1%")
                .startDate(sdf.parse("20240101"))
                .endDate(sdf.parse("20240321"))
                .build();
        inventoryMapper.getReleaseList(cri).forEach(System.out::println);
    }

    @Test
    public void testGetInvCalc() throws ParseException {
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
        inventoryMapper.getInvCalcData(cri);
    }

    @Test
    public void testInvReport() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        inventoryMapper.getInvReport(Criteria.builder()
                .startDate(sdf.parse("20240101"))
                .endDate(sdf.parse("20240131"))
                .type("unit")
                .build()).forEach(System.out::println);
    }


}
