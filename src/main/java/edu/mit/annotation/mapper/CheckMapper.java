package edu.mit.annotation.mapper;

import edu.mit.annotation.test2dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CheckMapper {
    //검수계획 리스트 가져오기
    List<OrderList> selectOrderList();

    // 검수계획 리스트에서 선택한 발주서 정보 보여주기
    OrderInfo selectpoinfo(String id,String code);

    // 검수계획 발주서의 해당하는 검수일정 넣기
    void progressPlan1(String p1, String p10, Date p10_1);

    void progressPlan2(String p1, String p11, Date p11_1);

    //날짜별 조회
    List<OrderList> dateSelect(Date date1, Date date2);

    //페이징 처리
    List<CheckList> getListWithPaging(Criteria cri);



    // 검색기능
    List<OrderList> searchPlan(String search,String option);

    //검수처리 리스트 보여주기
    List<CheckList> selectCheckList();

    CheckInfo selectCheckinfo(String number,String code);


    ProgressCheckItemDTO progressResult();





}
