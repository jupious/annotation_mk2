package edu.mit.annotation.mapper;

import edu.mit.annotation.realdto.Criteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
public class OrderMapperTests {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testGetPrcPlan() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Criteria cri = Criteria.builder()
                .pageNum(0)
                .amount(10)
                .type("CN")
                .keyword("%태일%")
                .startDate(sdf.parse("20240122"))
                .endDate(sdf.parse("20240222"))
                .build();
        orderMapper.getProcPlanList(cri).forEach(System.out::println);
        orderMapper.getSearchDataCount();
    }

    @Test
    public void testGetPOITEMS(){
        orderMapper.getPurchOrderItemList("'prc-13','prc-14','prc-18','prc-19'").forEach(System.out::println);
    }

    @Test
    public void testPOList() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Criteria cri = Criteria.builder()
                .pageNum(0)
                .amount(10)
                .type("CN")
                .keyword("%젬%")
                .startDate(sdf.parse("20240122"))
                .endDate(sdf.parse("20240222"))
                .build();
        orderMapper.getPoList(cri).forEach(System.out::println);
    }

    @Test
    public void testGetPOITEMS2(){
        orderMapper.getPOitems("po-4").forEach(System.out::println);
    }

    @Test
    public void testGetCompInfo(){
        orderMapper.getCompanyInfo("101-81-93005");
    }
}
