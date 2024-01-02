package edu.mit.annotation.testdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckDTO {
    // 차수
    private String num;

    //검수일정
    private Date checkDate;

    //검수결과
    private String result;

    //제작수량합계
    private Integer sum;

    //보완사항
    private String etc;

}
