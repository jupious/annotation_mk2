package edu.mit.annotation.realdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequest {

    private Integer bno;         // PK
    private String title;        // 제목
    private String content;      // 내용
    private String writer;       // 작성자

}