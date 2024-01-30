package edu.mit.annotation.mapper;

import edu.mit.annotation.dto.CoOpCompany;
import edu.mit.annotation.dto.ProdCodeWithNameDTO;
import edu.mit.annotation.dto.ProductionPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComMapper {

    void com(CoOpCompany company);

    void plan(ProductionPlan prod);

    List<ProductionPlan> calList();

    void update(ProductionPlan prod);

    void delete(String prod_plan_code);

    List<ProdCodeWithNameDTO> autoProductCode(String product_code);

}
