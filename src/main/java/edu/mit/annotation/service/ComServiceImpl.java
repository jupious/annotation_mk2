package edu.mit.annotation.service;

import edu.mit.annotation.dto.CoOpCompany;
import edu.mit.annotation.dto.ProdCodeWithNameDTO;
import edu.mit.annotation.dto.ProductionPlan;
import edu.mit.annotation.mapper.ComMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ComServiceImpl implements ComService{
    @Autowired
    private ComMapper mapper;

    @Override
    public void com(CoOpCompany company) {
        System.out.println("Service의 값 :::" + company);
        mapper.com(company);

    }

    @Override
    public void plan(ProductionPlan prod) {
        System.out.println("Service에서 받은 값 :: "+prod);
        mapper.plan(prod);
    }

    @Override
    public List<ProductionPlan> calList() {
        return mapper.calList();
    }

    @Override
    public void update(ProductionPlan prod) {
        mapper.update(prod);

    }

    @Override
    public void delete(String prod_plan_code) {
        mapper.delete(prod_plan_code);
    }

    @Override
    public List<ProdCodeWithNameDTO> autoPcPn(String product_code) {
        return mapper.autoProductCode(product_code);
    }
}
