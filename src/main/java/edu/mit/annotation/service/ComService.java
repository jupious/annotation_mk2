package edu.mit.annotation.service;

import edu.mit.annotation.dto.CoOpCompany;
import edu.mit.annotation.dto.ProdCodeWithNameDTO;
import edu.mit.annotation.dto.ProductionPlan;

import java.util.List;

public interface ComService {

    void com(CoOpCompany company);

    void plan(ProductionPlan prod);

    List<ProductionPlan> calList();

    void update(ProductionPlan prod);

    void delete(String prod_plan_code);

    List<ProdCodeWithNameDTO> autoPcPn(String product_code);
}
