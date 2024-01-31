package edu.mit.annotation.realdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {

    private Integer bno;                   // PK
    private String title;                  // 제목
    private String content;                // 내용
    private String writer;                 // 작성자
    private String email;
    private Integer view_cnt;               // 조회 수
    private Date created_date;     // 생성일시
    private Date modified_date;    // 최종 수정일시

}
