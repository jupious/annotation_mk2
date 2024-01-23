package edu.mit.annotation.service;

import edu.mit.annotation.mapper.CheckMapper;
import edu.mit.annotation.test2dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class CheckServiceImpl implements CheckService{
    //Mepper 주입할 자리
    @Autowired
    private CheckMapper mapper;

    @Override // 발주번호 리스트 가져오기
    public List<OrderList> selectOrderList() {

        return mapper.selectOrderList();
    }

    @Override // 발주번호 기준 검수계획 공급자정보와 품목정보 보여주기
    public OrderInfo selectpoinfo(String id,String code) {

        return mapper.selectpoinfo(id,code);
    }

    @Override // 발주 안에 품목별 검수 일정 등록하는 작업
    public void progressPlan(String p1, String p10, Date p10_1, String p11, Date p11_1) {

        mapper.progressPlan1(p1,p10,p10_1);
        mapper.progressPlan2(p1,p11,p11_1);
    }

    // 날짜에 따라 조회
    @Override
    public List<OrderList> dateSelect(Date date1, Date date2) {
        return mapper.dateSelect(date1,date2);
    }

    // 페이징 처리
//    @Override
//    public List<Criteria> getFreeBoard(PaginationInfo vo) {
//        int startRow = (vo.getPageNum() - 1) * vo.getPageNum();
//        return mapper.getFreeBoard(startRow, vo.getPageNum());
//    }

    // 검수계획의 검색기능
    @Override
    public List<OrderList> searchPlan(String search,String option){

            return mapper.searchPlan(search, option);


    }


    @Override // 검수 처리 리스트를 보여주는 메소드
    public List<CheckList> selectCheckList() {

        return mapper.selectCheckList();
    }

    //검수처리 정보 보여주기
    @Override
    public CheckInfo selectCheckinfo(String number, String code) {
        return mapper.selectCheckinfo(number, code);
    }


}






