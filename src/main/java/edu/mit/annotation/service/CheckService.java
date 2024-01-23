package edu.mit.annotation.service;

import edu.mit.annotation.test2dto.*;

import java.util.Date;
import java.util.List;

public interface CheckService {



        // 발주서 정보 가져오기(발주서번호) 리스트
        List<OrderList> selectOrderList();

        //발주
        // 발주 관련 데이터 보여줌
        OrderInfo selectpoinfo(String id,String code);

       // 진척 검수 일정 등록하는 작업 // 진척검수
       void progressPlan(String p1, String p10, Date p10_1, String p11, Date p11_1);

       List<OrderList> dateSelect(Date date1, Date date2);

//       // 페이징 처리
//       List<Criteria> getFreeBoard(PaginationInfo vo);
//
//        int getTotal(PaginationInfo vo);

        // 검수계획의 검색 기능
        List<OrderList> searchPlan(String search,String option);

    // 검수처리 리스트 가져오기(발주서번호) // 검수품목
        List<CheckList> selectCheckList();

        // 검수처리 관련 데이터 보여줌
        CheckInfo selectCheckinfo(String number,String code);



}
