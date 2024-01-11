package edu.mit.annotation.mapper;

import edu.mit.annotation.testdto.TestMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestMapper {

    List<TestMemberDTO> findAll();
}
