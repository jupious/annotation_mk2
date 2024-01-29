package edu.mit.annotation.service;

import edu.mit.annotation.realdto.Criteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
public class OrderServiceImplTests {
    @Autowired
    private OrderService orderService;

    @Test
    public void testGetPPWNP() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Criteria cri = Criteria.builder()
                .pageNum(0)
                .amount(10)
                .type("CN")
                .keyword("%%")
                .startDate(sdf.parse("20240122"))
                .endDate(sdf.parse("20240222"))
                .build();
        System.out.println(orderService.getProcPlanListWithNoPO(cri));
    }

    @Test
    public void testGetPublishedPOList() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Criteria cri = Criteria.builder()
                .pageNum(0)
                .amount(10)
                .type("CN")
                .keyword("%%")
                .startDate(sdf.parse("20240122"))
                .endDate(sdf.parse("20240222"))
                .build();
        System.out.println(orderService.getPublishedPOList(cri));
    }
}
