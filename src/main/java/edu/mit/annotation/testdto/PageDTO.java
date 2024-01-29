package edu.mit.annotation.testdto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

    //화면에서 페이지바를 표시하기 위해 필요한 정보
    private int startPage,endPage;
    private boolean prev,next;

    private RegisterCriteria cri; //현재페이지,페이당 글개수
    private long total; //전체 글개수

    public PageDTO(RegisterCriteria cri, long total){
        this.cri=cri;
        this.total=total;

        //페이지바의 끝페이지= (올림(현재페이지/10))*10
        endPage =(int)((Math.ceil(cri.getPageNum() /10.0))*10);
        //페이지바의 시작페이지 = 끝페이지-9
        startPage=endPage-9;

        //진짜마지막 페이지=소수점올림(전체글개수/한페이지당개수)
        int realEnd= (int)(Math.ceil((total*1.0)/cri.getAmount()));

        //페이바의 끝페이지가 진짜마지막페이지보다 크면 진짜로 변경
        if(endPage>realEnd)
            endPage=realEnd;

        //endPage가 끝페이가 아니라면 next가 true
        next = (endPage != realEnd);

        //시작페이지가 1이 아닐때 prev가 true;
        prev = (startPage != 1);

    }

}
